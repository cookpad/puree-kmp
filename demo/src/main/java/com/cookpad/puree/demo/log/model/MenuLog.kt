package com.cookpad.puree.demo.log.model

import com.cookpad.puree.PureeLog
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuLog(
    @SerialName("menu_name")
    val menuName: String,
) : PureeLog
