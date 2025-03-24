@file:Suppress("FunctionNaming")

package com.cookpad.puree

import com.cookpad.puree.model.ManualClock
import com.cookpad.puree.output.PureeBufferedLog
import com.cookpad.puree.output.PureeBufferedOutput
import com.cookpad.puree.rule.LifecycleCoroutineRule
import com.cookpad.puree.store.PureeLogStore
import com.cookpad.puree.type.JsonObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.spy
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.io.IOException
import kotlin.math.pow
import kotlin.test.Test
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
class PureeBufferedOutputTest : LifecycleCoroutineRule() {

    private lateinit var logStore: PureeLogStore
    private lateinit var clock: ManualClock
    private lateinit var output: TestPureeBufferedOutput

    private val testJson = buildJsonObject {
        put("key", "value")
    }

    override fun setUp() {
        super.setUp()

        logStore = mock()
        clock = ManualClock(Clock.System.now())
        output = TestPureeBufferedOutput(OUTPUT_ID)

        output.initialize(
            logStore = logStore,
            clock = clock,
            coroutineContext = dispatcher,
        )
    }

    @Test
    fun emit() = runTest {
        // Act
        val time = clock.now()
        output.emit(testJson)
        clock.updateTime(1.seconds)
        advanceUntilIdle()

        // Assert
        verify(logStore).add(OUTPUT_ID, testJson.toBufferedLog(time))
    }

    @Test
    fun resume() = runTest {
        // Arrange
        output.emit(testJson)
        output.suspend()
        clock.updateTime(3.minutes)

        // Act
        output.resume()

        // Assert
        verify(logStore).get(OUTPUT_ID, output.logsPerFlush)
        output.suspend()
    }

    @Test
    fun suspend() = runTest {
        // Arrange
        output.emit(testJson)

        // Act
        output.suspend()
        clock.updateTime(3.minutes)

        // Assert
        verify(logStore, times(0)).get(OUTPUT_ID, output.logsPerFlush)
    }

    @Test
    fun flush() = runTest {
        // Arrange
        val output = spy(output)
        val bufferedLogs = listOf(testJson).map { it.toBufferedLog(clock.now()) }

        whenever(logStore.get(OUTPUT_ID, output.logsPerFlush)).thenReturn(bufferedLogs)

        // Act
        clock.updateTime(output.flushInterval)
        advanceTimeBy(output.flushInterval)
        output.resume()

        // Assert
        verify(output).emit(any(), any(), any())
        output.suspend()
    }

    @Test
    fun flush_purge() = runTest {
        // Arrange
        output.purgeableAge = 20.seconds

        val output = spy(output)
        val bufferedLogs = listOf(testJson).map { it.toBufferedLog(clock.now()) }

        whenever(logStore.get(OUTPUT_ID, output.logsPerFlush)).thenReturn(bufferedLogs)

        // Act
        clock.updateTime(output.flushInterval)
        advanceTimeBy(output.flushInterval)
        output.resume()

        // Act
        output.suspend()
        verify(logStore).purgeLogsWithAge(OUTPUT_ID, clock.now(), 20.seconds)
        verify(output).emit(any(), any(), any())
    }

    @Test
    fun flush_empty() = runTest {
        // Arrange
        val output = spy(output)
        whenever(logStore.get(OUTPUT_ID, output.logsPerFlush)).thenReturn(emptyList())

        // Act
        clock.updateTime(output.flushInterval)
        advanceTimeBy(output.flushInterval)
        output.resume()

        // Assert
        output.suspend()
        verify(output, times(0)).emit(any(), any(), any())
        verify(logStore, times(0)).remove(eq(OUTPUT_ID), any())
    }

    @Test
    fun flush_maxFlushSizeInBytes() = runTest {
        // Arrange
        val output = spy(output)
        val longJson = buildJsonObject { put("key", "value".repeat(1000)) }
        val bufferedLogs = listOf(longJson, testJson).map { it.toBufferedLog(clock.now()) }

        whenever(logStore.get(OUTPUT_ID, output.logsPerFlush)).thenReturn(bufferedLogs)
        output.maxFlushSizeInBytes = longJson.toString().toByteArray().size.toLong()

        // Act
        clock.updateTime(output.flushInterval)
        advanceTimeBy(output.flushInterval)
        output.resume()

        // Assert
        output.suspend()
        verify(output).emit(eq(listOf(longJson)), any(), any())
        verify(logStore).remove(OUTPUT_ID, bufferedLogs.take(1))
    }

    @Test
    fun flush_failed() = runTest {
        // Arrange
        val output = spy(output)
        val bufferedLogs = listOf(testJson).map { it.toBufferedLog(clock.now()) }

        whenever(logStore.get(OUTPUT_ID, output.logsPerFlush)).thenReturn(bufferedLogs)
        doAnswer { it.getArgument<(Throwable) -> Unit>(2).invoke(IOException()) }.whenever(output).emit(any(), any(), any())

        // Act
        clock.updateTime(output.flushInterval)
        advanceTimeBy(output.flushInterval)
        output.resume()

        // Assert
        output.suspend()
        verify(output).emit(eq(listOf(testJson)), any(), any())
        verify(logStore, times(0)).remove(OUTPUT_ID, bufferedLogs)
    }

    @Test
    fun flush_failedThenRetry() = runTest {
        // Arrange
        val output = spy(output)
        val bufferedLogs = listOf(testJson).map { it.toBufferedLog(clock.now()) }

        whenever(logStore.get(OUTPUT_ID, output.logsPerFlush)).thenReturn(bufferedLogs)
        doAnswer { it.getArgument<(Throwable) -> Unit>(2).invoke(IOException()) }
            .doAnswer { it.getArgument<() -> Unit>(1).invoke() }
            .whenever(output).emit(any(), any(), any())

        // Act
        clock.updateTime(output.flushInterval)
        advanceTimeBy(output.flushInterval)
        output.resume()
        clock.updateTime(output.flushInterval)
        advanceTimeBy(output.exponentialBackoffBase)

        // Assert
        output.suspend()
        verify(output).emit(eq(listOf(testJson)), any(), any())
    }

    @Test
    fun flush_failedMaxRetry() = runTest {
        // Arrange
        val output = spy(output)
        val bufferedLogs = listOf(testJson).map { it.toBufferedLog(clock.now()) }

        whenever(logStore.get(OUTPUT_ID, output.logsPerFlush)).thenReturn(bufferedLogs)
        doAnswer { it.getArgument<(Throwable) -> Unit>(2).invoke(IOException()) }.whenever(output).emit(any(), any(), any())

        // Act
        clock.updateTime(output.flushInterval)
        advanceTimeBy(output.flushInterval)
        output.resume()

        repeat(output.maxRetryCount) {
            val step = output.exponentialBackoffBase.absoluteValue.times(2.0.pow((it).toDouble()))
            clock.updateTime(step)
            advanceTimeBy(step)
        }

        clock.updateTime(output.flushInterval)
        advanceTimeBy(output.flushInterval)

        // Assert
        output.suspend()
        verify(output, times(output.maxRetryCount + 1)).emit(eq(listOf(testJson)), any(), any())
    }

    private fun JsonObject.toBufferedLog(createdAt: Instant) = PureeBufferedLog(
        createdAt = createdAt,
        log = this,
    )

    private class TestPureeBufferedOutput(uniqueId: String) : PureeBufferedOutput(uniqueId) {

        init {
            flushInterval = 1.minutes
        }

        override fun emit(logs: List<JsonObject>, onSuccess: () -> Unit, onFailed: (Throwable) -> Unit) {
            onSuccess.invoke()
        }
    }

    companion object {
        private const val OUTPUT_ID = "output"
    }
}
