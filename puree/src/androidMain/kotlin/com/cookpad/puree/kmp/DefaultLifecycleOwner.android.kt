package com.cookpad.puree.kmp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner

actual class DefaultLifecycleOwner : LifecycleOwner {
    actual override val lifecycle: Lifecycle = ProcessLifecycleOwner.get().lifecycle
}
