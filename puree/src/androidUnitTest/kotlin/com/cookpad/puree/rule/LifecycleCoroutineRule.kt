package com.cookpad.puree.rule

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.testing.TestLifecycleOwner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

@OptIn(ExperimentalCoroutinesApi::class)
open class LifecycleCoroutineRule {
    lateinit var dispatcher: TestDispatcher
        private set
    lateinit var lifecycleOwner: TestLifecycleOwner
        private set

    @BeforeTest
    open fun setUp() {
        dispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(dispatcher)
        lifecycleOwner = TestLifecycleOwner(Lifecycle.State.STARTED)
    }

    @AfterTest
    open fun tearDown() {
        lifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        Dispatchers.resetMain()
    }
}
