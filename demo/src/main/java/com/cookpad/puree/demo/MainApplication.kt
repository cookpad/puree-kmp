package com.cookpad.puree.demo

import android.app.Application

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Puree.initialize(this)
    }
}
