package com.msarpong.mydays.ui.calendar

import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.msarpong.mydays.R


class CalendarScreen : AppCompatActivity() {

    private lateinit var titleText: TextView
    private lateinit var calendarView: CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_screen)

        setupView()
    }

    private fun setupView() {
        calendarView = findViewById(R.id.calendar_diary)
        titleText = findViewById(R.id.tv_calendar)
        titleText.setText("Calendar")

        calendarView.setOnDateChangeListener(OnDateChangeListener { view, year, month, dayOfMonth ->
            val Date = dayOfMonth.toString() + "-" + (month + 1) + "-" + year
            Log.d("Date listener", Date)
            Toast.makeText(this, Date, Toast.LENGTH_LONG).show()
        })
    }


}
