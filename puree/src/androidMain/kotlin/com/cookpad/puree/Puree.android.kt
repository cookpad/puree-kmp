package com.cookpad.puree

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Lifecycle
import com.cookpad.puree.output.PureeBufferedOutput
import com.cookpad.puree.output.PureeOutput
import com.cookpad.puree.serializer.DefaultPureeLogSerializer
import com.cookpad.puree.serializer.PureeLogSerializer
import com.cookpad.puree.store.PureeLogStore
import com.cookpad.puree.type.PlatformClass
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.datetime.Clock
import kotlin.reflect.KClass

actual class Puree(
    private val logStore: PureeLogStore,
    private val logSerializer: PureeLogSerializer = DefaultPureeLogSerializer(),
    private val lifecycle: Lifecycle = defaultLifecycleOwner.lifecycle,
) {
    @VisibleForTesting
    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    internal val dispatcher = newSingleThreadContext("PureeLogger")

    @VisibleForTesting
    internal var clock: Clock = Clock.System

    private val configuredLogs: MutableMap<PlatformClass<out PureeLog>, Configuration> = mutableMapOf()
    private val outputIds: MutableSet<String> = mutableSetOf()
    private val bufferedOutputs: MutableList<PureeBufferedOutput> = mutableListOf()

    fun filter(filter: PureeFilter, vararg logTypes: KClass<out PureeLog>): Puree {
        logTypes.forEach {
            configuredLogs.getOrPut(PlatformClass(it)) { Configuration() }.filters.add(filter)
        }

        return this
    }

    fun output(output: PureeOutput, vararg logTypes: KClass<out PureeLog>): Puree {
        if (output is PureeBufferedOutput) {
            require(output.uniqueId !in outputIds) { "Cannot register another PureeBufferedOutput with uniqueId: ${output.uniqueId}." }

            outputIds.add(output.uniqueId)
            bufferedOutputs.add(output)
        }

        logTypes.forEach {
            configuredLogs.getOrPut(PlatformClass(it)) { Configuration() }.outputs.add(output)
        }

        return this
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
