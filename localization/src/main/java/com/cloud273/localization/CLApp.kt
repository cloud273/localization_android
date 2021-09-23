package com.cloud273.localization

import android.app.Application
import androidx.lifecycle.LifecycleObserver

internal class CLApp : Application(), LifecycleObserver {

    companion object {
        internal lateinit var instance: CLApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}

