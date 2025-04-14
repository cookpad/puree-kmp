package com.cookpad.puree.kmp

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.cookpad.puree.kmp.output.PureeBufferedOutput
import com.cookpad.puree.kmp.output.PureeOutput
import com.cookpad.puree.kmp.serializer.PureeLogSerializer
import com.cookpad.puree.kmp.store.PureeLogStore
import com.cookpad.puree.kmp.type.PlatformClass
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

/**
 * Main logger class for the Puree-KMP logging system.
 *
 * PureeLogger is responsible for processing and routing logs to appropriate outputs
 * based on configured filters. It integrates with the application lifecycle to manage
 * buffered outputs efficiently.
 *
 * @param lifecycle The application lifecycle used to manage logging states
 * @param logSerializer Serializer to convert log objects to JSON format
 * @param logStore Storage for persisting logs
 * @param dispatcher CoroutineDispatcher for asynchronous log processing
 * @param clock Clock instance for timestamping logs
 * @param registeredLogs Map of registered log types and their configurations
 * @param bufferedOutputs List of outputs that support buffering
 */
class PureeLogger internal constructor(
    lifecycle: Lifecycle,
    private val logSerializer: PureeLogSerializer,
    private val logStore: PureeLogStore,
    private val dispatcher: CoroutineDispatcher,
    private val clock: Clock,
    private val defaultFilters: List<PureeFilter>,
    private val defaultOutputs: List<PureeOutput>,
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

    /**
     * Posts a log entry to be processed and routed to configured outputs.
     *
     * This method serializes the log, applies all configured filters, and sends
     * the processed log to all registered outputs for the log type. The processing
     * is done asynchronously in the configured coroutine dispatcher.
     *
     * Note: This method requires a PlatformClass parameter, which is platform-specific.
     * It is recommended to use the platform-specific `send` extension function instead:
     * - On Android: `pureeLogger.send(log)` (uses reified type parameters)
     * - On iOS: `pureeLogger.send(log, clazz)` (requires Objective-C class reference)
     *
     * @param T The type of log being posted, must extend PureeLog
     * @param log The log instance to be processed
     * @param platformClass Platform-specific class information for the log type
     * @throws LogNotRegisteredException If the log type is not registered with the logger
     */
    fun <T : PureeLog> postLog(log: T, platformClass: PlatformClass<T>) {
        val config = registeredLogs[platformClass.simpleName]
        val filters = config?.filters.orEmpty() + defaultFilters
        val outputs = config?.outputs.orEmpty() + defaultOutputs

        if (filters.isEmpty() && outputs.isEmpty()) {
            throw LogNotRegisteredException()
        }

        scope.launch {
            runCatching {
                filters.fold(logSerializer.serialize(log, platformClass)) { logJson, filter ->
                    filter.applyFilter(logJson) ?: throw SkippedLogException()
                }
            }.onSuccess {
                outputs.forEach { output ->
                    output.emit(it)
                }
            }.onFailure {
                if (it !is SkippedLogException) {
                    Napier.e("Failed to process log: $log", it)
                }
            }
        }
    }

    /**
     * Flushes all buffered outputs immediately.
     *
     * This method forces any logs that are being held in buffered outputs
     * to be processed and sent to their destinations immediately, rather than
     * waiting for their normal flush interval.
     *
     * The operation is performed asynchronously in the configured coroutine dispatcher.
     */
    fun flush() {
        scope.launch {
            bufferedOutputs.forEach { it.requestFlush() }
        }
    }

    /**
     * Suspends all buffered outputs.
     *
     * This method is called when the application goes to the background
     * (onStop lifecycle event). It suspends the processing of buffered outputs
     * to conserve resources. The operation is performed asynchronously in the
     * configured coroutine dispatcher.
     */
    fun suspend() {
        scope.launch {
            if (isResumed) {
                isResumed = false
                bufferedOutputs.forEach { it.suspend() }
            }
        }
    }

    /**
     * Resumes all buffered outputs.
     *
     * This method is called when the application comes to the foreground
     * (onStart lifecycle event). It resumes the processing of buffered outputs
     * that were previously suspended. The operation is performed asynchronously
     * in the configured coroutine dispatcher.
     */
    fun resume() {
        scope.launch {
            if (!isResumed) {
                isResumed = true
                bufferedOutputs.forEach { it.resume() }
            }
        }
    }
}

/**
 * Internal configuration class for each log type.
 *
 * This data class holds the filters and outputs configured for a specific log type.
 * When a log is posted, its type is used to look up the corresponding Configuration,
 * which determines how the log is processed and where it is sent.
 *
 * @property filters List of filters to apply to logs of this type
 * @property outputs List of outputs where logs of this type should be sent
 */
internal data class Configuration(
    val filters: MutableList<PureeFilter> = mutableListOf(),
    val outputs: MutableList<PureeOutput> = mutableListOf(),
)
