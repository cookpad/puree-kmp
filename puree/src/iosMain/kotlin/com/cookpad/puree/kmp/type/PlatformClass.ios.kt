package com.cookpad.puree.kmp.type

import com.cookpad.puree.kmp.PureeLog
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ObjCClass
import platform.Foundation.NSStringFromClass

/**
 * iOS implementation of the platform-specific class wrapper for PureeLog types.
 *
 * This implementation uses Objective-C class references (ObjCClass) to represent
 * log types in iOS. It converts the Objective-C class to a string using NSStringFromClass
 * to provide the class name for log type identification.
 *
 * @param T The type of PureeLog this class represents
 * @param clazz The Objective-C class reference for the log type
 * @property simpleName The string representation of the Objective-C class name
 */
@OptIn(BetaInteropApi::class)
actual class PlatformClass<T : PureeLog>(private val clazz: ObjCClass) {
    actual val simpleName: String
        get() = NSStringFromClass(clazz)
}
