package com.cookpad.puree.kmp.type

import com.cookpad.puree.kmp.PureeLog

/**
 * Platform-specific class wrapper for PureeLog types.
 *
 * This class provides a common interface for platform-specific class references,
 * allowing the logging system to work with class information in a platform-independent way.
 * Each platform (Android, iOS) implements this class differently to handle its specific
 * class reference mechanism.
 *
 * @param T The type of PureeLog this class represents
 * @property simpleName The simple name of the class, used for log type identification
 */
expect class PlatformClass<T : PureeLog> {
    val simpleName: String
}
