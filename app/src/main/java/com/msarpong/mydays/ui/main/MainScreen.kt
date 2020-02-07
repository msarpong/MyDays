package com.msarpong.mydays.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.add.ChoiceScreen
import org.msarpong.mydays.Db.Notes
import java.text.SimpleDateFormat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msarpong.mydays.ui.calendar.CalendarScreen
import com.msarpong.mydays.ui.setting.SettingScreen

import java.util.*

class MainScreen : AppCompatActivity() {

    private lateinit var mainViewModel: MainScreenViewModel
    private lateinit var myTodayDate: TextView

    private lateinit var addButton: Button
    private lateinit var calendarButton: ImageButton
    private lateinit var settingButton: ImageButton

    private lateinit var mainAdapter: MainAdapter
    private lateinit var recyclerView_home: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(this).get(MainScreenViewModel::class.java)

        setContentView(R.layout.main_screen)
        // LayoutManager and Adapter
        mainAdapter = MainAdapter()
        recyclerView_home = findViewById(R.id.recyclerView_home)
        recyclerView_home.adapter = mainAdapter
        recyclerView_home.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        setupView()
        setupObserver()
    }

    private fun setupView() {
        myTodayDate = findViewById(R.id.tv_title)
        myTodayDate.setText(dateToday())

        addButton = findViewById(R.id.btn_add_new)
        calendarButton = findViewById(R.id.btn_calendar)
        settingButton = findViewById(R.id.btn_setting)

        addButton.setOnClickListener {
            val intent = Intent(this, ChoiceScreen::class.java)
            startActivity(intent)
        }
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
        mainViewModel.state.observe(this, Observer { state ->
            when (state) {
                is MainState.Error -> showError(state.error)
                is MainState.Success -> showDatas(state.dayNotes)
            }
        })
    }

    private fun showDatas(notes: List<Notes>) {
        mainAdapter.submitList(notes)

        notes.forEach {
            var temp = it.id.plus(", ")
                .plus(it.title).plus(",")
                .plus(it.type).plus(", ")
                .plus(it.text).plus(", ")
                .plus(it.mood).plus(", ")
            Log.i("ITEM: ", temp)
        }
    }

    private fun showError(error: Throwable) {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
    }


    private fun dateToday(): String {
        val pattern = "EEEE, dd MMMM yyyy"
        Locale.setDefault(Locale.ITALIAN)
        val current = SimpleDateFormat(pattern)
        val today = current.format(Date())
        return capitalize(today)
    }

    private fun capitalize(line: String): String {
        return Character.toUpperCase(line[0]) + line.substring(1)
    }

}
