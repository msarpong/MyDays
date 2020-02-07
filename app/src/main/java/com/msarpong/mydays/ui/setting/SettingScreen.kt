package com.msarpong.mydays.ui.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.add.ChoiceScreen
import com.msarpong.mydays.ui.calendar.CalendarScreen

class SettingScreen : AppCompatActivity() {

    private lateinit var calendarButton: ImageButton
    private lateinit var settingButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_screen)

        setupView()
    }

    private fun setupView() {

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
