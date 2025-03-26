package com.cookpad.puree.demo.log.filter

import com.cookpad.puree.kmp.PureeFilter
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

class AddTimeFilter : PureeFilter {
    override fun applyFilter(log: JsonObject): JsonObject {
        return JsonObject(log + ("time" to JsonPrimitive(System.currentTimeMillis())))
    }
}
