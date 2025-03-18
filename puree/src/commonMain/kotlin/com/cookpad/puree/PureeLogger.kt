package com.cookpad.puree

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.cookpad.puree.output.PureeBufferedOutput
import com.cookpad.puree.output.PureeOutput
import com.cookpad.puree.serializer.PureeLogSerializer
import com.cookpad.puree.store.PureeLogStore
import com.cookpad.puree.type.PlatformClass
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class PureeLogger internal constructor(
    lifecycle: Lifecycle,
    private val logSerializer: PureeLogSerializer,
    private val logStore: PureeLogStore,
    private val dispatcher: CoroutineDispatcher,
    private val clock: Clock,
    private val registeredLogs: Map<String, Configuration>,
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

    fun <T : PureeLog> postLog(log: T, platformClass: PlatformClass<T>) {
        val config = registeredLogs[platformClass.simpleName] ?: throw LogNotRegisteredException()

        scope.launch {
            runCatching {
                config.filters.fold(logSerializer.serialize(log, platformClass)) { logJson, filter ->
                    filter.applyFilter(logJson) ?: throw SkippedLogException()
                }
            }.onSuccess {
                config.outputs.forEach { output ->
                    output.emit(it)
                }
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
}

internal data class Configuration(
    val filters: MutableList<PureeFilter> = mutableListOf(),
    val outputs: MutableList<PureeOutput> = mutableListOf(),
)
