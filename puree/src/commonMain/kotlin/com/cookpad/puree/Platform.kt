package com.cookpad.puree

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform