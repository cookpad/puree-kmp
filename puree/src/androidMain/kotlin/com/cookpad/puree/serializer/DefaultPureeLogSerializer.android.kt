package com.cookpad.puree.serializer

import com.cookpad.puree.PureeLog
import com.cookpad.puree.formatter
import com.cookpad.puree.type.PlatformClass
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.serializer

actual class DefaultPureeLogSerializer : PureeLogSerializer {
    @OptIn(InternalSerializationApi::class)
    override fun <T : PureeLog> serialize(log: T, platformClass: PlatformClass<T>): JsonObject {
        return formatter.encodeToJsonElement(platformClass.kClass.serializer(), log).jsonObject
    }
}
