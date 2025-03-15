package com.cookpad.puree.serializer

import com.cookpad.puree.PureeLog
import com.cookpad.puree.type.JsonObject
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSJSONSerialization
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create
import kotlin.reflect.KClass

actual class DefaultPureeLogSerializer : PureeLogSerializer {
    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    override fun <T : PureeLog> serialize(log: T, clazz: KClass<T>): JsonObject {
        val json = NSJSONSerialization.dataWithJSONObject(log, 0UL, null)
        val str = json?.let { NSString.create(it, NSUTF8StringEncoding) }

        return str!!
    }
}
