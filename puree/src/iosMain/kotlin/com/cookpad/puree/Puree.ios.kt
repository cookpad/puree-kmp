package com.cookpad.puree

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Lifecycle
import com.cookpad.puree.output.PureeBufferedOutput
import com.cookpad.puree.output.PureeOutput
import com.cookpad.puree.serializer.PureeLogSerializer
import com.cookpad.puree.store.PureeLogStore
import com.cookpad.puree.type.PlatformClass
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ObjCClass
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.datetime.Clock
import platform.Foundation.NSArray
import platform.Foundation.NSStringFromClass

actual class Puree(
    private val logStore: PureeLogStore,
    private val logSerializer: PureeLogSerializer,
    private val lifecycle: Lifecycle = defaultLifecycleOwner.lifecycle,
) {
    @VisibleForTesting
    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    internal val dispatcher = newSingleThreadContext("PureeLogger")

    @VisibleForTesting
    internal var clock: Clock = Clock.System

    private val configuredLogs: MutableMap<String, Configuration> = mutableMapOf()
    private val outputIds: MutableSet<String> = mutableSetOf()
    private val bufferedOutputs: MutableList<PureeBufferedOutput> = mutableListOf()

    init {
        Napier.base(DebugAntilog())
    }

    @OptIn(BetaInteropApi::class)
    fun filter(filter: PureeFilter, logTypes: NSArray): Puree {
        logTypes.toList<ObjCClass>().forEach {
            configuredLogs.getOrPut(NSStringFromClass(it)) { Configuration() }.filters.add(filter)
        }

        return this
    }

    @OptIn(BetaInteropApi::class)
    fun output(output: PureeOutput, logTypes: NSArray): Puree {
        Napier.d { "output: $output" }
        if (output is PureeBufferedOutput) {
            require(output.uniqueId !in outputIds) { "Cannot register another PureeBufferedOutput with uniqueId: ${output.uniqueId}." }

            outputIds.add(output.uniqueId)
            bufferedOutputs.add(output)

            Napier.d { "added buffered output: ${output.uniqueId}" }
        }

        logTypes.toList<ObjCClass>().forEach {
            configuredLogs.getOrPut(NSStringFromClass(it)) { Configuration() }.outputs.add(output)
        }

        return this
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> NSArray.toList(): List<T> {
        val result = mutableListOf<T>()

        for (i in 0L until this.count.toLong()) {
            result.add(this.objectAtIndex(i.toULong()) as T)
        }

        return result
    }

    fun build(): PureeLogger {
        return PureeLogger(
            lifecycle = lifecycle,
            logSerializer = logSerializer,
            logStore = logStore,
            dispatcher = dispatcher,
            clock = clock,
            registeredLogs = configuredLogs,
            bufferedOutputs = bufferedOutputs,
        )
    }
}

@OptIn(BetaInteropApi::class)
fun PureeLogger.postLog(log: String, clazz: ObjCClass) {
    postLog(log, PlatformClass(clazz))
}
