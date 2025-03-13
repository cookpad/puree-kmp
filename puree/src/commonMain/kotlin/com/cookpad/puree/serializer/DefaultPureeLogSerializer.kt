package com.cookpad.puree.serializer

import com.cookpad.puree.PureeLog
import com.cookpad.puree.formatter
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

class DefaultPureeLogSerializer : PureeLogSerializer {
    override fun <T : PureeLog> serialize(log: T, serializer: KSerializer<T>): JsonObject {
        return formatter.encodeToJsonElement(serializer, log).jsonObject
    }
}
