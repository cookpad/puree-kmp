package com.cookpad.puree.kmp.type

import kotlinx.serialization.json.JsonObject

actual typealias JsonObject = JsonObject

internal actual fun String.toJsonObject() = formatter.decodeFromString<JsonObject>(this)
