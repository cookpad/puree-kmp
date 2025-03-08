package com.cookpad.puree.output

import kotlinx.datetime.Instant
import kotlinx.serialization.json.JsonObject

/**
 * Log object that is buffered to a [com.cookpad.puree.store.PureeLogStore].
 * Used by [com.cookpad.puree.output.PureeBufferedOutput]
 *
 */
data class PureeBufferedLog(
    /**
     * The unique identifier of this log.
     */
    val id: Long = 0,
    /**
     * The date and time when this log is posted.
     */
    val createdAt: Instant,
    /**
     * The log serialized in JSON format.
     */
    val log: JsonObject,
)
