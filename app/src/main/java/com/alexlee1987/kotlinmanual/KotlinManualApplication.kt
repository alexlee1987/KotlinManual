package com.alexlee1987.kotlinmanual

import android.app.Application

class KotlinManualApplication: Application() {
    companion object {
        private lateinit var sInstance: KotlinManualApplication

        fun getInstance(): KotlinManualApplication {
            return sInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this
    }

}