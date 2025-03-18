package com.cookpad.puree.demo.log.output

import android.util.Log
import com.cookpad.puree.output.PureeBufferedOutput
import kotlinx.serialization.json.JsonObject
import kotlin.time.Duration.Companion.seconds

class PurgeableLogcatWarningBufferedOutput(uniqueId: String) : PureeBufferedOutput(uniqueId) {

    init {
        flushInterval = 10.seconds
        purgeableAge = 5.seconds
    }

    override fun emit(logs: List<JsonObject>, onSuccess: () -> Unit, onFailed: (Throwable) -> Unit) {
        Log.w(this::class.java.simpleName, "Logs: $logs")
        onSuccess()
    }
}
