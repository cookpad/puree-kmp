package com.cookpad.puree.kmp.type

actual typealias JsonObject = String

internal actual fun String.toJsonObject() = this
