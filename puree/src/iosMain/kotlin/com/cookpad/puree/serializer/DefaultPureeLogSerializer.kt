package com.cookpad.puree.serializer

import com.cookpad.puree.type.JsonObject
import com.cookpad.puree.type.PlatformClass
import kotlinx.serialization.serializer

internal class DefaultPureeLogSerializer : PureeLogSerializer {
    override fun <T : Any> serialize(log: T, platformClass: PlatformClass<T>): JsonObject {
        return log as JsonObject
    }
}
