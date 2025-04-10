package com.cookpad.puree.kmp

import androidx.annotation.VisibleForTesting
import com.cookpad.puree.kmp.output.PureeBufferedOutput
import com.cookpad.puree.kmp.output.PureeOutput
import com.cookpad.puree.kmp.serializer.PureeLogSerializer
import com.cookpad.puree.kmp.store.PureeLogStore
import com.cookpad.puree.kmp.type.PlatformClass
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

/**
 * iOS implementation of the Puree builder class for creating and configuring a [PureeLogger] instance.
 *
 * This class provides methods for configuring filters and outputs for different log types,
 * and a build method for creating a PureeLogger with the configured settings.
 *
 * @param logStore Storage for persisting logs
 * @param logSerializer Serializer to convert log objects to JSON format
 */
actual class Puree(
    private val logStore: PureeLogStore,
    private val logSerializer: PureeLogSerializer,
) {
    @VisibleForTesting
    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    internal val dispatcher = newSingleThreadContext("PureeLogger")

    @VisibleForTesting
    internal var clock: Clock = Clock.System

    private val defaultFilters: MutableList<PureeFilter> = mutableListOf()
    private val defaultOutputs: MutableList<PureeOutput> = mutableListOf()
    private val configuredLogs: MutableMap<String, Configuration> = mutableMapOf()
    private val outputIds: MutableSet<String> = mutableSetOf()
    private val bufferedOutputs: MutableList<PureeBufferedOutput> = mutableListOf()

    init {
        Napier.base(DebugAntilog())
    }

    /**
     * Registers the default filter.
     * @see filter for more information about filters.
     *
     * @param filters The filters to be applied to all log types by default
     * @return This Puree instance for method chaining
     */
    fun defaultFilter(vararg filters: PureeFilter): Puree {
        defaultFilters.addAll(filters)
        return this
    }

    /**
     * Registers the default output destination.
     * @see output for more information about outputs.
     *
     * @param outputs The outputs to be used for all log types by default
     * @return This Puree instance for method chaining
     * @throws IllegalArgumentException If attempting to register a buffered output with a duplicate uniqueId
     */
    fun defaultOutput(vararg outputs: PureeOutput): Puree {
        outputs.forEach {
            if (it is PureeBufferedOutput) {
                require(it.uniqueId !in outputIds) { "Cannot register another PureeBufferedOutput with uniqueId: ${it.uniqueId}." }
                outputIds.add(it.uniqueId)
                bufferedOutputs.add(it)
            }
        }

        defaultOutputs.addAll(outputs)
        return this
    }

    /**
     * Registers a filter to be applied to the specified log types.
     *
     * Filters are used to modify or conditionally process logs before they are sent to outputs.
     * Multiple filters can be registered for the same log type, and they will be applied in the order they were registered.
     *
     * @param filter The filter to apply to the specified log types
     * @param logTypes NSArray of Objective-C classes representing the log types to which the filter should be applied
     * @return This Puree instance for method chaining
     */
    @OptIn(BetaInteropApi::class)
    fun filter(filter: PureeFilter, logTypes: NSArray): Puree {
        logTypes.toList<ObjCClass>().forEach {
            configuredLogs.getOrPut(NSStringFromClass(it)) { Configuration() }.filters.add(filter)
        }

        return this
    }

    /**
     * Registers an output destination for the specified log types.
     *
     * Outputs define where logs will be sent (e.g., console, file, network).
     * Multiple outputs can be registered for the same log type.
     * If the output is a buffered output, it will be managed by the logger's lifecycle.
     *
     * @param output The output destination to register
     * @param logTypes NSArray of Objective-C classes representing the log types to send to this output
     * @return This Puree instance for method chaining
     * @throws IllegalArgumentException If attempting to register a buffered output with a duplicate uniqueId
     */
    @OptIn(BetaInteropApi::class)
    fun output(output: PureeOutput, logTypes: NSArray): Puree {
        if (output is PureeBufferedOutput) {
            require(output.uniqueId !in outputIds) { "Cannot register another PureeBufferedOutput with uniqueId: ${output.uniqueId}." }

            outputIds.add(output.uniqueId)
            bufferedOutputs.add(output)
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

    /**
     * Builds and returns a configured PureeLogger instance.
     *
     * This method creates a new PureeLogger with all the configured filters, outputs,
     * and other settings. The logger will be integrated with the iOS lifecycle
     * using a DefaultLifecycleOwner created specifically for iOS.
     *
     * @return A fully configured PureeLogger instance ready for use
     */
    fun build(): PureeLogger {
        return PureeLogger(
            lifecycle = DefaultLifecycleOwner().lifecycle,
            logSerializer = logSerializer,
            logStore = logStore,
            dispatcher = dispatcher,
            clock = clock,
            defaultFilters = defaultFilters,
            defaultOutputs = defaultOutputs,
            registeredLogs = configuredLogs,
            bufferedOutputs = bufferedOutputs,
        )
    }
}

/**
 * Extension function to send a log to the PureeLogger.
 *
 * This iOS-specific extension function provides a way to send logs
 * using Objective-C class references, which is the standard way to
 * work with class types in iOS.
 *
 * @param log The log instance to be processed
 * @param clazz The Objective-C class representing the type of the log
 */
@OptIn(BetaInteropApi::class)
fun PureeLogger.send(log: PureeLog, clazz: ObjCClass) {
    postLog(log, PlatformClass(clazz))
}
