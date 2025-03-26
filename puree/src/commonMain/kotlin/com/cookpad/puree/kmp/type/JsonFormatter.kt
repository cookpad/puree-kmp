package com.cookpad.puree.kmp.type

import kotlinx.serialization.json.Json

internal val formatter = Json {
    isLenient = true
    prettyPrint = true
    ignoreUnknownKeys = true
    coerceInputValues = true
    encodeDefaults = true
    explicitNulls = false
}
