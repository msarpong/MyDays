package com.msarpong.mydays.ui.detailDate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.calendar.CalendarScreen
import com.msarpong.mydays.ui.main.MainAdapter
import com.msarpong.mydays.ui.setting.SettingScreen
import org.msarpong.mydays.Db.Notes


class DateScreen : AppCompatActivity() {

    private lateinit var dateViewModel: DateScreenViewModel
    private lateinit var myTodayDate: TextView
    private lateinit var calendarButton: ImageButton
    private lateinit var settingButton: ImageButton
    private lateinit var mainAdapter: MainAdapter
    private lateinit var recyclerView_home: RecyclerView

    private lateinit var dateNote: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.date_screen)
        dateViewModel = ViewModelProviders.of(this).get(DateScreenViewModel::class.java)
        mainAdapter = MainAdapter()
        recyclerView_home = findViewById(R.id.recyclerView_home)
        recyclerView_home.adapter = mainAdapter
        recyclerView_home.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        dateNote = intent.getStringExtra("Date")
        setupView()
        setupObserver()
    }

    private fun setupView() {
        myTodayDate = findViewById(R.id.tv_title)
        calendarButton = findViewById(R.id.btn_calendar)
        settingButton = findViewById(R.id.btn_setting)

        dateViewModel.send(DateEvent.Load, dateNote)
        myTodayDate.setText(dateNote)

        calendarButton.setOnClickListener {
            val intent = Intent(this, CalendarScreen::class.java)
            startActivity(intent)
        }

        settingButton.setOnClickListener {
            val intent = Intent(this, SettingScreen::class.java)
            startActivity(intent)
        }
    }

    private fun setupObserver() {
        dateViewModel.state.observe(this, Observer { state ->
            when (state) {
                is DateState.Error -> showError(state.error)
                is DateState.Success -> showDatas(state.dayNotes)
            }
        })
    }


    private fun showDatas(notes: List<Notes>) {
        mainAdapter.submitList(notes)
        notes.forEach {
            Log.i("DATE_DETAIL: ", it.id.toString())
        }
    }

    private fun showError(error: Throwable) {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
    }

}
