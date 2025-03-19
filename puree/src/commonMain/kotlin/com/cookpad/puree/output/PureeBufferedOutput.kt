package com.cookpad.puree.output

import com.cookpad.puree.store.PureeLogStore
import com.cookpad.puree.type.JsonObject
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
 * The output that emits posted logs in batches.
 */
abstract class PureeBufferedOutput(
    /**
     * Uniquely identifies this output.
     * This values is used to match the registered [PureeBufferedOutput] to the buffered logs when saving and retrieving
     * to and from the [PureeLogStore].
     * Changing this value will prevent the log from being processed correctly.
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
     * Emits the logs.
     *
     * @param logs The logs in JSON format
     * @param onSuccess Should be invoked if successful.
     * @param onFailed Should be invoked if failed.
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
            logStore.add(
                outputId = uniqueId,
                bufferedLog = PureeBufferedLog(
                    createdAt = clock.now(),
                    log = log,
                ),
            )
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

                runCatching { flush() }
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
     * Flushes the buffered logs stored in [logStore]. If [purgeableAge] is set, old logs will be
     * deleted accordingly. The batch will contain at most [logsPerFlush] and if
     * [maxFlushSizeInBytes], the batch will be trimmed accordingly. If emitted successfully, the
     * logs will be deleted from the [logStore].
     *
     * @see resume
     */
    internal suspend fun flush() {
        purgeableAge?.let {
            logStore.purgeLogsWithAge(uniqueId, clock.now(), it)
        }

        val bufferedLogs = logStore.get(uniqueId, logsPerFlush).let { bufferedLogs ->
            when (maxFlushSizeInBytes) {
                Long.MAX_VALUE -> bufferedLogs
                else -> {
                    var size = 0L
                    bufferedLogs.takeWhile {
                        size += it.log.toString().encodeToByteArray().size
                        size <= maxFlushSizeInBytes
                    }
                }
            }
        }

        if (bufferedLogs.isEmpty()) {
            return
        }

        suspendCancellableCoroutine { continuation ->
            emit(
                logs = bufferedLogs.map { it.log },
                onSuccess = { continuation.resume(Unit) },
                onFailed = { throwable -> continuation.resumeWithException(throwable) },
            )
        }

        logStore.remove(uniqueId, bufferedLogs)
    }
}
