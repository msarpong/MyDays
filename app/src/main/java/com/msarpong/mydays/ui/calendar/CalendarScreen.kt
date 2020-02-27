package com.msarpong.mydays.ui.calendar

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.detailDate.DateScreen
import com.msarpong.mydays.ui.setting.SettingScreen

const val SHARED_PREFS_SETTING = "Settings_prefs"
const val SHARED_PREFS_THEME = "Theme"
const val DARK_MODE = "DARK"
const val LIGHT_MODE = "LIGHT"

class CalendarScreen : AppCompatActivity() {
    private lateinit var calendarButton: ImageButton
    private lateinit var settingButton: ImageButton
    private lateinit var calendarView: CalendarView
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
        setContentView(R.layout.calendar_screen)
        setupView()
    }

    private fun setupView() {
        calendarView = findViewById(R.id.calendar_diary)
        calendarView.setOnDateChangeListener(OnDateChangeListener { view, year, month, dayOfMonth ->
            val Date = dayOfMonth.toString() + "/" + (month + 1) + "/" + year
            val intent = Intent(this, DateScreen::class.java)
            intent.putExtra("Date", Date)
            startActivity(intent)
        })

        calendarButton = findViewById(R.id.btn_calendar)
        settingButton = findViewById(R.id.btn_setting)


        calendarButton.setOnClickListener {
            val intent = Intent(this, CalendarScreen::class.java)
            startActivity(intent)
        }

        settingButton.setOnClickListener {
            val intent = Intent(this, SettingScreen::class.java)
            startActivity(intent)
        }
    }


}
