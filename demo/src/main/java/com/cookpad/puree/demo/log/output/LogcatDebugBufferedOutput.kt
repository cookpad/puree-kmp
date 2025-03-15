package com.cookpad.puree.demo.log.output

import android.util.Log
import com.cookpad.puree.output.PureeBufferedOutput
import kotlinx.serialization.json.JsonObject

class LogcatDebugBufferedOutput(uniqueId: String) : PureeBufferedOutput(uniqueId) {
    override val flushInterval: Long = 5000

    override fun emit(logs: List<JsonObject>, onSuccess: () -> Unit, onFailed: (Throwable) -> Unit) {
        Log.d(this::class.java.simpleName, "Logs: $logs")
        onSuccess()
    }
}
