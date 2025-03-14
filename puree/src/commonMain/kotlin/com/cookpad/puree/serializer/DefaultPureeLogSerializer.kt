package com.cookpad.puree.serializer

import com.cookpad.puree.PureeLog
import com.cookpad.puree.formatter
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

class DefaultPureeLogSerializer : PureeLogSerializer {
    @OptIn(InternalSerializationApi::class)
    override fun <T : PureeLog> serialize(log: T, clazz: KClass<T>): JsonObject {
        return formatter.encodeToJsonElement(clazz.serializer(), log).jsonObject
    }
}
