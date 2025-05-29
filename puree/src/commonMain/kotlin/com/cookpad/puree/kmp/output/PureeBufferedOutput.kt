package com.cookpad.puree.kmp.output

import com.cookpad.puree.kmp.store.PureeLogStore
import com.cookpad.puree.kmp.type.JsonObject
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.math.max
import kotlin.math.pow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * Abstract base class for outputs that buffer logs before emitting them in batches.
 *
 * PureeBufferedOutput extends the basic PureeOutput interface to provide buffering capabilities,
 * which allows for more efficient processing of logs. Instead of emitting each log immediately,
 * this class stores logs temporarily and emits them in batches according to configurable criteria.
 *
 * Buffered outputs are lifecycle-aware and will automatically suspend and resume
 * their flushing operations based on the application's lifecycle events.
 *
 * Implementations must provide the actual emission logic by implementing the
 * abstract emit method that handles batches of logs.
 */
abstract class PureeBufferedOutput(
    /**
     * Uniquely identifies this output.
     * This values is used to match the registered [PureeBufferedOutput] to the buffered logs when saving and retrieving
     * to and from the [PureeLogStore].
     */
    internal val uniqueId: String,
) : PureeOutput {
    /**
     * The frequency interval when the buffer is flushed to be emitted in batches. Behavior is
     * undefined if set to a negative value.
     */
    open var flushInterval: Duration = 2.minutes

    /**
     * The maximum number of logs in a batch.
     */
    open var logsPerFlush: Int = 100

    /**
     * The number of times to retry a failed batch.
     */
    open var maxRetryCount: Int = 5

    /**
     * The base duration to wait when retrying a failed batch. Negative duration is automatically converted to absolute value.
     */
    open var exponentialBackoffBase: Duration = 2.seconds

    /**
     * If set to a non-null value, logs of this age will be not be processed and deleted. Behavior
     * is undefined if set to a negative value.
     */
    open var purgeableAge: Duration? = null

    /**
     * If set, maximum number of bytes to include in a single flush
     */
    open var maxFlushSizeInBytes: Long = Long.MAX_VALUE

    private lateinit var logStore: PureeLogStore
    private lateinit var clock: Clock
    private lateinit var nextFlush: Instant
    private lateinit var coroutineScope: CoroutineScope

    private var retryCount: Int = 0

    /**
     * Emits a batch of logs to their destination.
     *
     * This abstract method must be implemented by concrete subclasses to define how
     * batches of logs are sent to their final destination. Unlike the emit method from
     * PureeOutput which handles single logs, this method processes multiple logs at once
     * for more efficient delivery.
     *
     * @param logs The list of logs serialized in JSON format to be emitted
     * @param onSuccess Callback to be invoked when the batch is successfully delivered
     * @param onFailed Callback to be invoked with the exception when delivery fails
     */
    abstract fun emit(logs: List<JsonObject>, onSuccess: () -> Unit, onFailed: (Throwable) -> Unit)

    internal fun initialize(
        logStore: PureeLogStore,
        clock: Clock,
        coroutineContext: CoroutineContext,
    ) {
        this.logStore = logStore
        this.clock = clock
        nextFlush = clock.now() + flushInterval
        retryCount = 0
        coroutineScope = CoroutineScope(coroutineContext + SupervisorJob(coroutineContext[Job]))
    }

    /**
     * Stores the log instead of emitting immediately
     */
    final override fun emit(log: JsonObject) {
        // Guaranteed to be executed in the appropriate thread
        coroutineScope.launch {
            val bufferedLog = PureeBufferedLog(
                createdAt = clock.now(),
                log = log,
            )

            runCatching {
                logStore.add(
                    outputId = uniqueId,
                    bufferedLog = bufferedLog,
                )
            }.recoverCatching {
                Napier.e { "Failed to store log: $log" }
                flush(listOf(bufferedLog))
            }.onFailure {
                Napier.e { "Failed to store & flush log: $log, error: ${it.message}" }
            }
        }
    }

    /**
     * Create and run a coroutine that periodically flushes and emit the buffered logs according to
     * [flushInterval]. Failed attempts will be retried according to [exponentialBackoffBase] and
     * [maxRetryCount].
     *
     * @see suspend
     */
    internal fun resume() {
        coroutineScope.launch {
            while (isActive) {
                val nextFlushTime = nextFlush.toEpochMilliseconds() - clock.now().toEpochMilliseconds()
                val delayMillis = max(0, nextFlushTime)

                delay(delayMillis)
                requestFlush()
            }
        }
    }

    /**
     * Cancels the coroutine that periodically flushes and emit the buffered logs.
     *
     * @see resume
     */
    internal fun suspend() {
        coroutineScope.coroutineContext.cancelChildren()
    }

    /**
     * Requests to flush the buffered logs. If the flush fails, it will retry according to
     * [exponentialBackoffBase] and [maxRetryCount]. If the maximum retry count is reached, it will
     * reset the retry count and wait for [flushInterval] before trying again.
     *
     * @see resume
     */
    internal suspend fun requestFlush() {
        runCatching { flush(logStore.get(uniqueId, logsPerFlush)) }
            .onSuccess {
                retryCount = 0
                nextFlush = clock.now() + flushInterval
            }
            .onFailure {
                retryCount++

                if (retryCount > maxRetryCount) {
                    retryCount = 0
                    nextFlush = clock.now() + flushInterval
                } else {
                    nextFlush = clock.now() + exponentialBackoffBase.absoluteValue.times(
                        2.0.pow((retryCount - 1).toDouble()),
                    )
                }
            }
    }

    /**
     * Flushes the buffered logs stored in [logStore]. If [purgeableAge] is set, old logs will be
     * deleted accordingly. The batch will contain at most [logsPerFlush] and if
     * [maxFlushSizeInBytes], the batch will be trimmed accordingly. If emitted successfully, the
     * logs will be deleted from the [logStore].
     *
     * @see resume
     */
    private suspend fun flush(bufferedLogs: List<PureeBufferedLog>) {
        purgeableAge?.let {
            logStore.purgeLogsWithAge(uniqueId, clock.now(), it)
        }

        val trimmedBufferedLogs = when (maxFlushSizeInBytes) {
            Long.MAX_VALUE -> bufferedLogs
            else -> {
                var size = 0L
                bufferedLogs.takeWhile {
                    size += it.log.toString().encodeToByteArray().size
                    size <= maxFlushSizeInBytes
                }
            }
        }

        if (trimmedBufferedLogs.isEmpty()) {
            return
        }

        suspendCancellableCoroutine { continuation ->
            emit(
                logs = trimmedBufferedLogs.map { it.log },
                onSuccess = { continuation.resume(Unit) },
                onFailed = { throwable -> continuation.resumeWithException(throwable) },
            )
        }

        logStore.remove(uniqueId, trimmedBufferedLogs)
    }
}
