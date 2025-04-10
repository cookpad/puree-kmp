package com.cookpad.puree.kmp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner

actual class DefaultLifecycleOwner : LifecycleOwner by ProcessLifecycleOwner.get()
