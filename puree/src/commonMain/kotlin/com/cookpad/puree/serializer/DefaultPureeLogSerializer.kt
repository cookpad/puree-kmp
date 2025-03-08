package com.cookpad.puree.serializer

import com.cookpad.puree.PureeLog
import com.cookpad.puree.formatter
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject

class DefaultPureeLogSerializer: PureeLogSerializer {
    override fun serialize(log: PureeLog): JsonObject {
        return formatter.encodeToJsonElement(log).jsonObject
    }
}
