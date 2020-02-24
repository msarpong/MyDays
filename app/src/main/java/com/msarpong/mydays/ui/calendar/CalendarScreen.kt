package com.msarpong.mydays.ui.calendar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.detailDate.DateScreen

class CalendarScreen : AppCompatActivity() {

    private lateinit var calendarView: CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_screen)
        setupView()
    }

    private fun setupView() {
        calendarView = findViewById(R.id.calendar_diary)
        calendarView.setOnDateChangeListener(OnDateChangeListener { view, year, month, dayOfMonth ->
            val Date = dayOfMonth.toString() + "/" + (month + 1) + "/" + year
            val intent = Intent(this, DateScreen::class.java)
            intent.putExtra("Date",Date)
            startActivity(intent)
        })
    }


}
