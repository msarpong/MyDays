package com.msarpong.mydays.ui.setting

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.calendar.CalendarScreen


const val SHARED_PREFS_SETTING = "Settings_prefs"
const val SHARED_PREFS_THEME = "Theme"
const val DARK_MODE = "DARK"
const val LIGHT_MODE = "LIGHT"

class SettingScreen : AppCompatActivity() {

    private lateinit var calendarButton: ImageButton
    private lateinit var settingButton: ImageButton
    private lateinit var themeSwitch: TextView
    private lateinit var securitySwitch: Switch
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = getSharedPreferences(SHARED_PREFS_SETTING, Context.MODE_PRIVATE)
        val myTheme = sharedPrefs.getString(SHARED_PREFS_THEME, LIGHT_MODE)

        if (myTheme == DARK_MODE) {
            setTheme(R.style.DarkTheme);
        } else if (myTheme == LIGHT_MODE) {
            setTheme(R.style.LightTheme);
        }

        setContentView(R.layout.setting_screen)
        setupView()
    }

    private fun setupView() {

        themeSwitch = findViewById(R.id.switch_theme)
        calendarButton = findViewById(R.id.btn_calendar)
        settingButton = findViewById(R.id.btn_setting)
        securitySwitch = findViewById(R.id.switch_security)

        calendarButton.setOnClickListener {
            val intent = Intent(this, CalendarScreen::class.java)
            startActivity(intent)
        }

        settingButton.setOnClickListener {
            val intent = Intent(this, SettingScreen::class.java)
            startActivity(intent)
        }

        themeSwitch.setOnClickListener {

            val myTheme = sharedPrefs.getString(SHARED_PREFS_THEME, LIGHT_MODE)
            if (myTheme == DARK_MODE) {
                sharedPrefs.edit().putString(SHARED_PREFS_THEME, LIGHT_MODE).apply()

            } else if (myTheme == LIGHT_MODE) {
                sharedPrefs.edit().putString(SHARED_PREFS_THEME, DARK_MODE).apply()

            }

            finish()
            restartActivity()
        }


    }

    private fun restartActivity() {
        startActivity(Intent(this, SettingScreen::class.java))
    }


//    private fun merda(){
//        tvChangeTheme.setOnClickListener(object : OnClickListener() {
//            fun onClick(v: View?) {
//                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                } else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                }
//                finish()
//                startActivity(Intent(this@MainActivity, this@MainActivity.getClass()))
//            }
//        })
//    }
}
