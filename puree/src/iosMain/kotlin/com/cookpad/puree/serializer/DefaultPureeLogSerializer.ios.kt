package com.cookpad.puree.serializer

import com.cookpad.puree.PureeLog
import com.cookpad.puree.type.JsonObject
import com.cookpad.puree.type.PlatformClass
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSJSONSerialization
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create

actual class DefaultPureeLogSerializer : PureeLogSerializer {
    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    override fun <T : PureeLog> serialize(log: T, platformClass: PlatformClass<T>): JsonObject {
        val json = NSJSONSerialization.dataWithJSONObject(log, 0UL, null)
        val str = json?.let { NSString.create(it, NSUTF8StringEncoding) }

        return str!!
    }
}
