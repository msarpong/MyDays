package com.msarpong.mydays.utils

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        instance = this
    }
}