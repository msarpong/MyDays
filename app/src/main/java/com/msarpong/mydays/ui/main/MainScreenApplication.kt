package com.msarpong.mydays.ui.main

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class MainScreenApplication : Application() {

    companion object {
        lateinit var instance: MainScreenApplication
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        instance = this
    }
}