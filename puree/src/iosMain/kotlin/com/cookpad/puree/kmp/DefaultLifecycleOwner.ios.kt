package com.cookpad.puree.kmp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

actual class DefaultLifecycleOwner : LifecycleOwner {
    override val lifecycle: Lifecycle = LifecycleRegistry(this).apply {
        currentState = Lifecycle.State.STARTED
    }
}
