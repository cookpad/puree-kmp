@file:Suppress("FunctionNaming")

package com.cookpad.puree

import com.cookpad.puree.model.ManualClock
import com.cookpad.puree.model.TestLog
import com.cookpad.puree.model.TestLogSerializer
import com.cookpad.puree.output.PureeBufferedOutput
import com.cookpad.puree.output.PureeOutput
import com.cookpad.puree.rule.LifecycleCoroutineRule
import com.cookpad.puree.store.PureeLogStore
import com.cookpad.puree.type.JsonObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PureeLoggerTest : LifecycleCoroutineRule() {

    private lateinit var logStore: PureeLogStore
    private lateinit var output: PureeOutput
    private lateinit var bufferedOutput: PureeBufferedOutput
    private lateinit var clock: ManualClock

    override fun setUp() {
        super.setUp()

        logStore = mock()
        output = mock()
        bufferedOutput = mock()
        clock = ManualClock(Clock.System.now())
    }

    @Test
    fun postLog() = runTest {
        // Arrange
        val filter = mock<PureeFilter>()
        var capturedLog: JsonObject? = null

        whenever(filter.applyFilter(any())).thenAnswer { invocation ->
            val json = invocation.getArgument<JsonObject>(0)
            capturedLog = json
            json
        }

        val logger = createPureeBuilder()
            .filter(filter, TestLog::class)
            .output(output, TestLog::class)
            .output(bufferedOutput, TestLog::class)
            .build()

        // Act
        logger.send(TestLog(sequence = 1))

        // Assert
        verify(filter).applyFilter(any())
        verify(output).emit(capturedLog!!)
        verify(bufferedOutput).emit(capturedLog!!)
    }

    @Test
    fun postLog_skipped() = runTest {
        // Arrange
        val filterSkip: PureeFilter = mock {
            on { applyFilter(any()) } doReturn null
        }
        val filter: PureeFilter = mock {
            on { applyFilter(any()) } doAnswer { invocation ->
                invocation.getArgument(0)
            }
        }
        val puree = createPureeBuilder()
            .filter(filterSkip, TestLog::class)
            .filter(filter, TestLog::class)
            .output(output, TestLog::class)
            .build()

        // Act
        puree.send(TestLog(sequence = 1))

        // Assert
        verify(filter, never()).applyFilter(any())
        verify(output, never()).emit(any())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun flush() = runTest {
        val outputs = (1..5).map { index ->
            mock<PureeBufferedOutput> {
                on { uniqueId } doReturn "buffered_output_$index"
            }
        }

        val puree = createPureeBuilder().apply {
            outputs.forEach {
                output(it, TestLog::class)
            }
        }.build()

        // Act
        puree.flush()
        advanceUntilIdle()

        // Assert
        outputs.forEach { output ->
            verify(output).flush()
        }
    }

    private fun createPureeBuilder(): Puree {
        return Puree(
            logStore = logStore,
            logSerializer = TestLogSerializer(),
            lifecycle = lifecycleOwner.lifecycle,
        ).apply {
            clock = this@PureeLoggerTest.clock
            dispatcher = this@PureeLoggerTest.dispatcher
        }
    }
}
