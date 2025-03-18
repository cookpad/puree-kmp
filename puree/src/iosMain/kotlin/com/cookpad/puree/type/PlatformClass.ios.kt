package com.cookpad.puree.type

import com.cookpad.puree.PureeLog
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ObjCClass
import platform.Foundation.NSStringFromClass

@OptIn(BetaInteropApi::class)
actual class PlatformClass<T : PureeLog>(private val clazz: ObjCClass) {
    actual val simpleName: String
        get() = NSStringFromClass(clazz)
}
