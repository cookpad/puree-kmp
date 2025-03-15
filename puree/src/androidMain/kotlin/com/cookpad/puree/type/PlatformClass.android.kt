package com.cookpad.puree.type

import kotlin.reflect.KClass

actual class PlatformClass<T: Any>(val kClass: KClass<T>) {
    actual val simpleName: String
        get() = kClass.simpleName.orEmpty()
}
