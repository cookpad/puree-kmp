package com.cookpad.puree.demo.log.model

import com.cookpad.puree.PureeLog
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PeriodicLog(
    @SerialName("sequence")
    val sequence: Int,
) : PureeLog
