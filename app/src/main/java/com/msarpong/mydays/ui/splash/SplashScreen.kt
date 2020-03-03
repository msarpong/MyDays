package com.msarpong.mydays.ui.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.main.MainScreen
import com.msarpong.mydays.utils.getThemeInfo
import java.util.*
import kotlin.concurrent.schedule

const val MAIN_SCREEN_TIME = 2000L
const val SHARED_PREFS_SETTING = "Settings_prefs"
const val SHARED_PREFS_THEME = "Theme"
const val DARK_MODE = "DARK"
const val LIGHT_MODE = "LIGHT"

class SplashScreen : AppCompatActivity() {

    private lateinit var sharedPrefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPrefs = getSharedPreferences(SHARED_PREFS_SETTING, Context.MODE_PRIVATE)
        setTheme(getThemeInfo(sharedPrefs.getString(SHARED_PREFS_THEME, DARK_MODE)))

        setContentView(R.layout.splash_screen)
        goToMain()
    }

    private fun goToMain() {
        val runnableOnboarding = Runnable {
            val main = Intent(this, MainScreen::class.java)
            startActivity(main)

            finish()
        }
        Handler().postDelayed(runnableOnboarding, MAIN_SCREEN_TIME)
    }


}

