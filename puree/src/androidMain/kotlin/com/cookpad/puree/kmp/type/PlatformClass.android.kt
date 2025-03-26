package com.cookpad.puree.kmp.type

import com.cookpad.puree.kmp.PureeLog
import kotlin.reflect.KClass

/**
 * Android implementation of the platform-specific class wrapper for PureeLog types.
 *
 * This implementation uses Kotlin's reflection API (KClass) to represent
 * log types in Android. It retrieves the simple name of the class using KClass.simpleName
 * to provide the class name for log type identification.
 *
 * @param T The type of PureeLog this class represents
 * @param kClass The Kotlin class reference for the log type
 * @property simpleName The simple name of the Kotlin class, or an empty string if null
 */
actual class PlatformClass<T : PureeLog>(val kClass: KClass<T>) {
    actual val simpleName: String
        get() = kClass.simpleName.orEmpty()
}
