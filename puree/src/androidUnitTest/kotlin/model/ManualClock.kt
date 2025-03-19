package model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration

class ManualClock(initialTime: Instant) : Clock {
    private var instant = initialTime

    override fun now(): Instant = instant

    fun updateTime(duration: Duration) {
        instant += duration
    }
}