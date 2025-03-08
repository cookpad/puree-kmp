package com.cookpad.puree

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import platform.Foundation.NSNotificationCenter
import platform.UIKit.UIApplicationDidBecomeActiveNotification
import platform.UIKit.UIApplicationDidEnterBackgroundNotification
import platform.UIKit.UIApplicationWillResignActiveNotification

actual val defaultLifecycleOwner: LifecycleOwner
    get() = DefaultLifecycleOwner()

internal class DefaultLifecycleOwner : LifecycleOwner {
    override val lifecycle: Lifecycle = LifecycleRegistry(this)

    init {
        NSNotificationCenter.defaultCenter().addObserverForName(
            name = UIApplicationDidBecomeActiveNotification,
            `object` = null,
            queue = null,
        ) {
            lifecycle.currentState = Lifecycle.State.RESUMED
        }

        NSNotificationCenter.defaultCenter().addObserverForName(
            name = UIApplicationWillResignActiveNotification,
            `object` = null,
            queue = null,
        ) {
            lifecycle.currentState = Lifecycle.State.STARTED
        }
    }
}
