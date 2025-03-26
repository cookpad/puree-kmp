package com.cookpad.puree.kmp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner

actual val defaultLifecycleOwner: LifecycleOwner
    get() = ProcessLifecycleOwner.get()
