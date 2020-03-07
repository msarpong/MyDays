package com.msarpong.mydays.ui.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.main.MainScreen
import com.msarpong.mydays.utils.*
import java.util.*
import kotlin.concurrent.schedule



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

