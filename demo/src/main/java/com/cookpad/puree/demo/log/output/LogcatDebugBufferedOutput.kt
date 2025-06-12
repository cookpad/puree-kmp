package com.cookpad.puree.demo.log.output

import android.util.Log
import com.cookpad.puree.kmp.output.PureeBufferedOutput
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import kotlin.time.Duration.Companion.seconds

class LogcatDebugBufferedOutput(uniqueId: String) : PureeBufferedOutput(uniqueId) {

    val scope = CoroutineScope(SupervisorJob())

    init {
        flushInterval = 5.seconds
    }

    override fun emit(logs: List<JsonObject>, onSuccess: () -> Unit, onFailed: (Throwable) -> Unit) {
        Log.d(this::class.java.simpleName, "Logs: $logs")
        onSuccess()

        scope.launch {
            delay(1000)
            onSuccess()
        }
    }
}
