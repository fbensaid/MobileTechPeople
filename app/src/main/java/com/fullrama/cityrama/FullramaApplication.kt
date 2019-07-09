package com.fullrama.cityrama

import android.app.Application

class FullramaApplication : Application() {

    companion object {
        lateinit var instance: FullramaApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}

