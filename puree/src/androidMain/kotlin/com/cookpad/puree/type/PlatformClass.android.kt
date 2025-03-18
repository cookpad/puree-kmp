package com.cookpad.puree.type

import com.cookpad.puree.PureeLog
import kotlin.reflect.KClass

actual class PlatformClass<T : PureeLog>(val kClass: KClass<T>) {
    actual val simpleName: String
        get() = kClass.simpleName.orEmpty()
}
