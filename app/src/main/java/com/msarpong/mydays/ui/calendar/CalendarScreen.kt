package com.msarpong.mydays.ui.calendar

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.detailDate.DateScreen
import com.msarpong.mydays.ui.setting.SettingScreen
import com.msarpong.mydays.utils.getThemeInfo
import com.msarpong.mydays.ui.splash.DARK_MODE
import com.msarpong.mydays.ui.splash.SHARED_PREFS_THEME
import com.msarpong.mydays.ui.splash.SHARED_PREFS_SETTING

class CalendarScreen : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var calendarButton: ImageButton
    private lateinit var settingButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = getSharedPreferences(SHARED_PREFS_SETTING, Context.MODE_PRIVATE)

        setTheme(getThemeInfo(sharedPrefs.getString(SHARED_PREFS_THEME, DARK_MODE)))
        setContentView(R.layout.calendar_screen)
        setupView()
    }

    private fun setupView() {
        calendarView = findViewById(R.id.calendar_diary)
        settingButton = findViewById(R.id.btn_setting)
        calendarButton = findViewById(R.id.btn_calendar)

        calendarButton.setOnClickListener {
            val intent = Intent(this, CalendarScreen::class.java)
            startActivity(intent)
        }

        settingButton.setOnClickListener {
            val intent = Intent(this, SettingScreen::class.java)
            startActivity(intent)
        }
        calendarView.setOnDateChangeListener(OnDateChangeListener { view, year, month, dayOfMonth ->
            val date = "0" + dayOfMonth.toString() + "/" + (month + 1) + "/" + year
            val intent = Intent(this, DateScreen::class.java)
            intent.putExtra("Date", date)
            startActivity(intent)

        })
    }
}
