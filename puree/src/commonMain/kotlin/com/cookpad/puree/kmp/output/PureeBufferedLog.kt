package com.cookpad.puree.kmp.output

import com.cookpad.puree.kmp.type.JsonObject
import kotlinx.datetime.Instant

/**
 * Data class representing a log entry that has been buffered for batch processing.
 *
 * PureeBufferedLog encapsulates a log that has been temporarily stored in a
 * [com.cookpad.puree.kmp.store.PureeLogStore] before being sent to its final destination.
 * It is used by [com.cookpad.puree.kmp.output.PureeBufferedOutput] to implement the buffering
 * mechanism that allows logs to be collected and processed in batches rather than individually.
 */
data class PureeBufferedLog(
    /**
     * The unique identifier of this log entry.
     */
    val id: Long = 0,
    /**
     * The date and time when this log entry was created.
     */
    val createdAt: Instant,
    /**
     * The actual log content serialized in JSON format.
     *
     * This property contains the serialized representation of the original log object
     * that was posted to the logger. The serialization is typically performed by a
     * [com.cookpad.puree.kmp.serializer.PureeLogSerializer] implementation.
     */
    val log: JsonObject,
)
