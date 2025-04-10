package com.cookpad.puree.kmp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

expect class DefaultLifecycleOwner() : LifecycleOwner {
    expect override val lifecycle: Lifecycle
}
