package com.cookpad.puree

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.cookpad.puree.output.PureeBufferedOutput
import com.cookpad.puree.output.PureeOutput
import com.cookpad.puree.serializer.DefaultPureeLogSerializer
import com.cookpad.puree.serializer.PureeLogSerializer
import com.cookpad.puree.store.PureeLogStore
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.datetime.Clock
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

class PureeLogger private constructor(
    lifecycle: Lifecycle,
    private val logSerializer: PureeLogSerializer,
    private val logStore: PureeLogStore,
    private val dispatcher: CoroutineDispatcher,
    private val clock: Clock,
    private val registeredLogs: Map<KClass<out PureeLog>, Configuration>,
    private val bufferedOutputs: List<PureeBufferedOutput>,
) {
    private val scope = CoroutineScope(dispatcher + SupervisorJob())
    private var isResumed = false

    init {
        bufferedOutputs.forEach { it.initialize(logStore, clock, dispatcher) }

        lifecycle.addObserver(
            object : DefaultLifecycleObserver {
                override fun onStart(owner: LifecycleOwner) {
                    resume()
                }

                override fun onStop(owner: LifecycleOwner) {
                    suspend()
                }
            },
        )
    }

    @OptIn(InternalSerializationApi::class)
    inline fun <reified T : PureeLog> postLog(log: T) {
        postLog(
            log = log,
            serializer = T::class.serializer(),
        )
    }

    fun <T : PureeLog> postLog(log: T, serializer: KSerializer<T>) {
        val config = registeredLogs[log::class] ?: throw LogNotRegisteredException()

        scope.launch {
            runCatching {
                config.filters.fold(logSerializer.serialize(log, serializer)) { logJson, filter ->
                    filter.applyFilter(logJson) ?: throw SkippedLogException()
                }
            }.onSuccess {
                config.outputs.forEach { output ->
                    output.emit(it)
                }
            }.onFailure {
                Napier.w { "Failed to post log: $it" }
            }
        }
    }

    fun flush() {
        scope.launch {
            bufferedOutputs.forEach { it.flush() }
        }
    }

    private fun suspend() {
        scope.launch {
            if (isResumed) {
                isResumed = false
                bufferedOutputs.forEach { it.suspend() }
            }
        }
    }

    private fun resume() {
        scope.launch {
            if (!isResumed) {
                isResumed = true
                bufferedOutputs.forEach { it.resume() }
            }
        }
    }

    class Builder(
        private val logStore: PureeLogStore,
        private val logSerializer: PureeLogSerializer = DefaultPureeLogSerializer(),
        private val lifecycle: Lifecycle = defaultLifecycleOwner.lifecycle,
    ) {
        @VisibleForTesting
        @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
        internal val dispatcher = newSingleThreadContext("PureeLogger")

        @VisibleForTesting
        internal var clock: Clock = Clock.System

        private val configuredLogs: MutableMap<KClass<out PureeLog>, Configuration> = mutableMapOf()
        private val outputIds: MutableSet<String> = mutableSetOf()
        private val bufferedOutputs: MutableList<PureeBufferedOutput> = mutableListOf()

        fun filter(filter: PureeFilter, vararg logTypes: KClass<out PureeLog>): Builder {
            logTypes.forEach {
                configuredLogs.getOrPut(it) { Configuration() }.filters.add(filter)
            }

            return this
        }

        fun output(output: PureeOutput, vararg logTypes: KClass<out PureeLog>): Builder {
            if (output is PureeBufferedOutput) {
                require(output.uniqueId !in outputIds) { "Cannot register another PureeBufferedOutput with uniqueId: ${output.uniqueId}." }

                outputIds.add(output.uniqueId)
                bufferedOutputs.add(output)
            }

            logTypes.forEach {
                configuredLogs.getOrPut(it) { Configuration() }.outputs.add(output)
            }

            return this
        }

        @OptIn(ExperimentalCoroutinesApi::class)
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
}

private data class Configuration(
    val filters: MutableList<PureeFilter> = mutableListOf(),
    val outputs: MutableList<PureeOutput> = mutableListOf(),
)
