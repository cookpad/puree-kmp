package com.cookpad.puree.demo

import android.content.Context
import com.cookpad.puree.demo.log.filter.AddTimeFilter
import com.cookpad.puree.demo.log.model.ClickLog
import com.cookpad.puree.demo.log.model.MenuLog
import com.cookpad.puree.demo.log.model.PeriodicLog
import com.cookpad.puree.demo.log.output.LogcatDebugBufferedOutput
import com.cookpad.puree.demo.log.output.LogcatOutput
import com.cookpad.puree.demo.log.output.PurgeableLogcatWarningBufferedOutput
import com.cookpad.puree.kmp.Puree
import com.cookpad.puree.kmp.PureeLog
import com.cookpad.puree.kmp.PureeLogger
import com.cookpad.puree.kmp.send
import com.cookpad.puree.kmp.serializer.DefaultPureeLogSerializer
import com.cookpad.puree.kmp.store.PlatformDefaultPureeLogStore

object Puree {
    var logger: PureeLogger? = null

    fun initialize(context: Context) {
        logger = Puree(
            logStore = PlatformDefaultPureeLogStore(context, "log.db"),
            logSerializer = DefaultPureeLogSerializer(),
        )
            .output(
                LogcatDebugBufferedOutput("logcat_debug"),
                ClickLog::class,
                MenuLog::class,
            )
            .output(
                PurgeableLogcatWarningBufferedOutput("logcat_warning"),
                PeriodicLog::class,
            )
            .defaultOutput(LogcatOutput())
            .defaultFilter(AddTimeFilter())
            .build()
    }

    inline fun <reified T : PureeLog> send(log: T) {
        logger?.send(log)
    }
}
