package com.cookpad.puree.kmp.model

import com.cookpad.puree.kmp.PureeLog
import com.cookpad.puree.kmp.serializer.PureeLogSerializer
import com.cookpad.puree.kmp.type.JsonObject
import com.cookpad.puree.kmp.type.PlatformClass
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

data class TestLog(
    val sequence: Int,
) : PureeLog

class TestLogSerializer : PureeLogSerializer {
    override fun <T : PureeLog> serialize(log: T, platformClass: PlatformClass<T>): JsonObject {
        return when (log) {
            is TestLog -> buildJsonObject { put("sequence", log.sequence) }
            else -> throw IllegalArgumentException("Unknown log type")
        }
    }
}
