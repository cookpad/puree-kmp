package com.cookpad.puree.type

import com.cookpad.puree.PureeLog

expect class PlatformClass<T : PureeLog> {
    val simpleName: String
}
