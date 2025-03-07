package com.cookpad.puree

import android.content.Context
import androidx.startup.Initializer

lateinit var pureeApplicationContext: Context
    private set

internal object PureeContext

internal class PureeInitializer : Initializer<PureeContext> {
    override fun create(context: Context): PureeContext {
        pureeApplicationContext = context.applicationContext
        return PureeContext
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
