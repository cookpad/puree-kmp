package com.cookpad.puree.output

import platform.darwin.UInt64
import kotlin.time.Duration.Companion.milliseconds

fun PureeBufferedOutput.setFlushInterval(flushIntervalMillis: Long) {
    flushInterval = flushIntervalMillis.toLong().milliseconds
}

fun PureeBufferedOutput.setLogsPerFlush(logsPerFlush: UInt) {
    this.logsPerFlush = logsPerFlush.toInt()
}

fun PureeBufferedOutput.setMaxRetryCount(maxRetryCount: UInt) {
    this.maxRetryCount = maxRetryCount.toInt()
}

fun PureeBufferedOutput.setExponentialBackoffBase(exponentialBackoffBaseMillis: UInt64) {
    exponentialBackoffBase = exponentialBackoffBaseMillis.toLong().milliseconds
}

fun PureeBufferedOutput.setPurgeableAge(purgeableAgeMillis: UInt64) {
    purgeableAge = purgeableAgeMillis.toLong().milliseconds
}
