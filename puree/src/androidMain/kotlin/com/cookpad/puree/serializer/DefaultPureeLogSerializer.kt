package com.cookpad.puree.serializer

import com.cookpad.puree.PureeLog
import com.cookpad.puree.type.JsonObject
import com.cookpad.puree.type.PlatformClass
import com.cookpad.puree.type.formatter
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.serializer

/**
 * Default Android implementation of the [PureeLogSerializer] interface.
 *
 * This class provides a standard serialization mechanism for Android using Kotlin's
 * serialization library. It converts PureeLog objects into JSON format by utilizing
 * the kotlinx.serialization framework, which requires that log classes are annotated
 * with @Serializable.
 *
 * The serialization process uses the platform class information to obtain the appropriate
 * serializer for each log type, ensuring type-safe serialization.
 */
class DefaultPureeLogSerializer : PureeLogSerializer {
    /**
     * Serializes a log object into JSON format using Kotlin's serialization library.
     *
     * This implementation uses the kotlinx.serialization framework to convert the log object
     * to JSON. It retrieves the appropriate serializer for the log type using the KClass
     * reference from the platformClass parameter, then encodes the log object to a JSON element
     * and returns its jsonObject property.
     *
     * @param log The log object to be serialized.
     * @param platformClass The platform-specific class information for the log type.
     * @return The serialized log as a JsonObject.
     */
    @OptIn(InternalSerializationApi::class)
    override fun <T : PureeLog> serialize(log: T, platformClass: PlatformClass<T>): JsonObject {
        return formatter.encodeToJsonElement(platformClass.kClass.serializer(), log).jsonObject
    }
}
