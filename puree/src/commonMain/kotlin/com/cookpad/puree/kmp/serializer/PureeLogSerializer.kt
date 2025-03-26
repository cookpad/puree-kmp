package com.cookpad.puree.kmp.serializer

import com.cookpad.puree.kmp.PureeLog
import com.cookpad.puree.kmp.type.JsonObject
import com.cookpad.puree.kmp.type.PlatformClass

/**
 * Interface for serializing log objects into JSON format.
 *
 * This interface defines the contract for converting PureeLog objects into a JSON representation
 * that can be processed by outputs. Implementations of this interface handle the platform-specific
 * serialization details, allowing the logging system to work with structured data across different platforms.
 *
 * An instance of this interface is provided to [com.cookpad.puree.kmp.PureeLogger] through the
 * [com.cookpad.puree.kmp.Puree] builder during initialization.
 */
interface PureeLogSerializer {
    /**
     * Serializes a log object into JSON format.
     *
     * This method converts a PureeLog object into a JSON representation that can be
     * processed by outputs. The serialization process may vary across platforms, but
     * the result should be a consistent JSON structure that represents the log data.
     *
     * The platformClass parameter provides platform-specific class information needed
     * for the serialization process, such as reflection data or class references.
     *
     * @param log The log object to be serialized.
     * @param platformClass Platform-specific class information for the log type.
     * @return The serialized log as a JsonObject.
     */
    fun <T : PureeLog> serialize(log: T, platformClass: PlatformClass<T>): JsonObject
}
