package com.cookpad.puree.serializer

import com.cookpad.puree.PureeLog
import com.cookpad.puree.type.JsonObject
import com.cookpad.puree.type.PlatformClass

/**
 * Serializes log objects into JSON format.
 * An instance of this interface are set to [com.cookpad.puree.PureeLogger] through [com.cookpad.puree.Puree].
 */
interface PureeLogSerializer {
    /**
     * Serialize the log into JSON format.
     *
     * @param log Log object to be serialized.
     * @param platformClass The platform class of the log object.
     *
     * @return Serialized log in JSON format.
     */
    fun <T : PureeLog> serialize(log: T, platformClass: PlatformClass<T>): JsonObject
}
