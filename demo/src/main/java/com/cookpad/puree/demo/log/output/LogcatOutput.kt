package com.cookpad.puree.demo.log.output

import android.util.Log
import com.cookpad.puree.output.PureeOutput
import kotlinx.serialization.json.JsonObject

class LogcatOutput : PureeOutput {
    override fun emit(log: JsonObject) {
        Log.d("Puree", log.toString())
    }
}
