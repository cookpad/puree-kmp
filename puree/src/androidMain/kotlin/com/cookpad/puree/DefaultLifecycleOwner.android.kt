package com.cookpad.puree

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner

actual val defaultLifecycleOwner: LifecycleOwner
    get() = ProcessLifecycleOwner.get()
