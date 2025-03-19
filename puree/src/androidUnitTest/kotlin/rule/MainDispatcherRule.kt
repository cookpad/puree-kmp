package rule

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

@OptIn(ExperimentalCoroutinesApi::class)
open class MainDispatcherRule(
    val dispatcher: TestDispatcher = UnconfinedTestDispatcher()
) {
    @BeforeTest
    open fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @AfterTest
    open fun tearDown() {
        Dispatchers.resetMain()
    }
}