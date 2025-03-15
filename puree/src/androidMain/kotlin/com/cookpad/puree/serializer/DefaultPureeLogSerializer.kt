package com.cookpad.puree.serializer

import com.cookpad.puree.formatter
import com.cookpad.puree.type.PlatformClass
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.serializer

class DefaultPureeLogSerializer : PureeLogSerializer {
    @OptIn(InternalSerializationApi::class)
    override fun <T : Any> serialize(log: T, platformClass: PlatformClass<T>): com.cookpad.puree.type.JsonObject {
        return formatter.encodeToJsonElement(platformClass.kClass.serializer(), log).jsonObject
    }
}
