package com.cookpad.puree.demo

import com.cookpad.puree.PureeLog
import com.cookpad.puree.PureeLogger
import com.cookpad.puree.demo.log.filter.AddTimeFilter
import com.cookpad.puree.demo.log.model.ClickLog
import com.cookpad.puree.demo.log.model.MenuLog
import com.cookpad.puree.demo.log.model.PeriodicLog
import com.cookpad.puree.demo.log.output.LogcatDebugBufferedOutput
import com.cookpad.puree.demo.log.output.LogcatOutput
import com.cookpad.puree.demo.log.output.PurgeableLogcatWarningBufferedOutput
import com.cookpad.puree.store.DefaultPureeLogStore

object Puree {
    private val logStore = DefaultPureeLogStore("log.db")
    private val logger: PureeLogger = PureeLogger.Builder(logStore)
        .filter(
            AddTimeFilter(),
            ClickLog::class, MenuLog::class, PeriodicLog::class
        )
        .output(
            LogcatOutput(),
            ClickLog::class, MenuLog::class, PeriodicLog::class
        )
        .output(
            LogcatDebugBufferedOutput("logcat_debug"),
            ClickLog::class, MenuLog::class
        )
        .output(
            PurgeableLogcatWarningBufferedOutput("logcat_warning"),
            PeriodicLog::class
        )
        .build()

    init {
        logger
    }

    fun send(log: PureeLog) {
        logger.postLog(log)
    }
}
