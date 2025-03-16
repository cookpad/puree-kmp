package com.cookpad.puree.type

actual typealias JsonObject = String

internal actual fun String.toJsonObject() = this
