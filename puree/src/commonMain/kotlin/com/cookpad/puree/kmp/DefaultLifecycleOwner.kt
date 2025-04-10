package com.cookpad.puree.kmp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

expect object DefaultLifecycleOwner : LifecycleOwner {
    override val lifecycle: Lifecycle
}
