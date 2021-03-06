package com.msarpong.mydays.ui.setting

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.calendar.CalendarScreen
import com.msarpong.mydays.ui.main.MainScreen
import com.msarpong.mydays.utils.*

class SettingScreen : AppCompatActivity() {

    private lateinit var calendarButton: ImageButton
    private lateinit var settingButton: ImageButton
    private lateinit var themeSwitch: TextView
    private lateinit var securitySwitch: Switch
    private lateinit var sharedPrefs: SharedPreferences

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        startActivity(Intent(this, MainScreen::class.java))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = getSharedPreferences(SHARED_PREFS_SETTING, Context.MODE_PRIVATE)

        setTheme(getThemeInfo(sharedPrefs.getString(SHARED_PREFS_THEME, DARK_MODE)))

        setContentView(R.layout.setting_screen)
        setupView()
    }

    private fun setupView() {

        themeSwitch = findViewById(R.id.switch_theme)
        calendarButton = findViewById(R.id.btn_calendar)
        settingButton = findViewById(R.id.btn_setting)
        securitySwitch = findViewById(R.id.switch_security)

        val myTheme = sharedPrefs.getString(SHARED_PREFS_THEME, LIGHT_MODE)

        if (myTheme == DARK_MODE) {
            themeSwitch.text = getString(R.string.disable_setting_theme)
        } else if (myTheme == LIGHT_MODE) {
            themeSwitch.text = getString(R.string.enable_setting_theme)
        }

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

}
