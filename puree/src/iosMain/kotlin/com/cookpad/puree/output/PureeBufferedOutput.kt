package com.cookpad.puree.output

import platform.darwin.UInt64
import kotlin.time.Duration.Companion.milliseconds

/**
 * Sets the flush interval for this buffered output.
 *
 * @param flushIntervalMillis The flush interval in milliseconds
 */
fun PureeBufferedOutput.setFlushInterval(flushIntervalMillis: Long) {
    flushInterval = flushIntervalMillis.toLong().milliseconds
}

/**
 * Sets the maximum number of logs to include in each flush operation.
 *
 * @param logsPerFlush The maximum number of logs per flush
 */
fun PureeBufferedOutput.setLogsPerFlush(logsPerFlush: UInt) {
    this.logsPerFlush = logsPerFlush.toInt()
}

/**
 * Sets the maximum number of retry attempts for failed flush operations.
 *
 * @param maxRetryCount The maximum number of retry attempts
 */
fun PureeBufferedOutput.setMaxRetryCount(maxRetryCount: UInt) {
    this.maxRetryCount = maxRetryCount.toInt()
}

/**
 * Sets the base duration for exponential backoff in retry attempts.
 * The exponential backoff duration is calculated as: base * (2^(retryCount-1))
 *
 * @param exponentialBackoffBaseMillis The base duration for exponential backoff in milliseconds
 */
fun PureeBufferedOutput.setExponentialBackoffBase(exponentialBackoffBaseMillis: UInt64) {
    exponentialBackoffBase = exponentialBackoffBaseMillis.toLong().milliseconds
}

/**
 * Sets the age threshold after which logs will be automatically purged.
 * Logs older than this threshold will be deleted during flush operations.
 *
 * @param purgeableAgeMillis The age threshold for log purging in milliseconds
 */
fun PureeBufferedOutput.setPurgeableAge(purgeableAgeMillis: UInt64) {
    purgeableAge = purgeableAgeMillis.toLong().milliseconds
}
