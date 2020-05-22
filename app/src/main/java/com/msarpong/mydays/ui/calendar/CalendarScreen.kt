package com.msarpong.mydays.ui.calendar

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.detailDate.DateScreen
import com.msarpong.mydays.ui.setting.SettingScreen
import com.msarpong.mydays.utils.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


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

        val a = calendarView.date
        calendarView.maxDate = a
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

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date =
                dayOfMonth.toString() + "/" + (month + 1) + "/" + year //todo change data 7/3/2020 to 07/03/2020

            val intent = Intent(this, DateScreen::class.java)
            intent.putExtra("Date", calendarDate(year, month, dayOfMonth))
            startActivity(intent)
        }


    }

}
