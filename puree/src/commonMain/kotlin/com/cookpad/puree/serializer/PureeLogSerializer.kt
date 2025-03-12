package com.cookpad.puree.serializer

import com.cookpad.puree.PureeLog
import kotlinx.serialization.json.JsonObject

/**
 * Serializes log objects into JSON format.
 * An instance of this interface are set to [com.cookpad.puree.kotlin.PureeLogger] through
 * [com.cookpad.puree.kotlin.PureeLogger.Builder]
 */
interface PureeLogSerializer {
    /**
     * Serialize the log into JSON format.
     *
     * @param log Log object to be serialized.
     *
     * @return Serialized log in JSON format.
     */
    fun serialize(log: PureeLog): JsonObject
}
