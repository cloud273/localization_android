package com.cloud273.localization.example

import android.app.Application
import com.cloud273.localization.CLLocalization

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CLLocalization.initialize(this, listOf("en", "vi"))
    }
}