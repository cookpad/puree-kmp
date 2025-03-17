package com.cookpad.puree.type

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ObjCClass
import platform.Foundation.NSStringFromClass

@OptIn(BetaInteropApi::class)
actual class PlatformClass<T : Any>(private val clazz: ObjCClass) {
    actual val simpleName: String
        get() = NSStringFromClass(clazz)
}
