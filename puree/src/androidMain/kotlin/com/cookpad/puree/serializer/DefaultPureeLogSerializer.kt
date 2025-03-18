package com.cookpad.puree.serializer

import com.cookpad.puree.PureeLog
import com.cookpad.puree.type.JsonObject
import com.cookpad.puree.type.PlatformClass
import com.cookpad.puree.type.formatter
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.serializer

class DefaultPureeLogSerializer : PureeLogSerializer {
    @OptIn(InternalSerializationApi::class)
    override fun <T : PureeLog> serialize(log: T, platformClass: PlatformClass<T>): JsonObject {
        return formatter.encodeToJsonElement(platformClass.kClass.serializer(), log).jsonObject
    }
}
