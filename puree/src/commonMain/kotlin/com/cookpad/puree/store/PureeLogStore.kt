package com.cookpad.puree.store

import com.cookpad.puree.output.PureeBufferedLog
import kotlinx.datetime.Instant
import kotlin.time.Duration

/**
 * Stores the buffered logs for batched emissions.
 * Implementations of this interface are guaranteed to be thread-safe (called from a single thread).
 *
 * @see DefaultPureeLogStore
 */
interface PureeLogStore {
    /**
     * Adds a new [PureeBufferedLog] to the store.
     *
     * @param outputId The id of the [com.cookpad.puree.output.PureeBufferedOutput] that owns this log.
     * @param bufferedLog The log to be stored
     */
    suspend fun add(outputId: String, bufferedLog: PureeBufferedLog)

    /**
     * Retrieves the logs from the store.
     *
     * @param outputId The id of the [com.cookpad.puree.output.PureeBufferedOutput] that owns the logs.
     * @param maxCount The maximum number of logs to retrieve.
     *
     * @return List of buffered logs.
     */
    suspend fun get(outputId: String, maxCount: Int): List<PureeBufferedLog>

    /**
     * Deletes the logs from the store.
     *
     * @param outputId The id of the [com.cookpad.puree.output.PureeBufferedOutput] that owns the logs.
     * @param bufferedLogs The logs to be deleted.
     */
    suspend fun remove(outputId: String, bufferedLogs: List<PureeBufferedLog>)

    /**
     * Deletes all the logs according the age of the log.
     *
     * @param outputId The id of the [com.cookpad.puree.output.PureeBufferedOutput] that owns the logs.
     * @param now The current date-time.
     * @param age The age of the of the logs to be deleted.
     */
    suspend fun purgeLogsWithAge(outputId: String, now: Instant, age: Duration)
}
