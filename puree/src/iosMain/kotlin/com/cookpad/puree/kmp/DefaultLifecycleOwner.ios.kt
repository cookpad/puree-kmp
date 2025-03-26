package com.cookpad.puree.kmp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

actual val defaultLifecycleOwner: LifecycleOwner
    get() = DefaultLifecycleOwner()

internal class DefaultLifecycleOwner : LifecycleOwner {
    override val lifecycle: Lifecycle = LifecycleRegistry(this).apply {
        currentState = Lifecycle.State.STARTED
    }
}
