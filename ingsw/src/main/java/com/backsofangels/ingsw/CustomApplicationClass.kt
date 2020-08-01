package com.backsofangels.ingsw

import android.app.Application
import com.facebook.stetho.Stetho

class CustomApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}