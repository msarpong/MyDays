package com.msarpong.mydays.ui.calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.msarpong.mydays.R

class CalendarScreen : AppCompatActivity() {

    private lateinit var titleText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_screen)

        setupView()
    }

    private fun setupView() {

        titleText = findViewById(R.id.tv_calendar)
        titleText.setText("Calendar")
    }
}
