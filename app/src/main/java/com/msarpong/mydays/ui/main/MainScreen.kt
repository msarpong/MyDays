package com.msarpong.mydays.ui.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.add.ChoiceScreen
import com.msarpong.mydays.ui.calendar.CalendarScreen
import com.msarpong.mydays.ui.setting.SettingScreen
import com.msarpong.mydays.utils.*
import org.msarpong.mydays.Db.Notes


class MainScreen : AppCompatActivity() {

    private lateinit var mainViewModel: MainScreenViewModel
    private lateinit var myTodayDate: TextView
    private lateinit var addButton: Button
    private lateinit var calendarButton: ImageButton
    private lateinit var settingButton: ImageButton
    private lateinit var mainAdapter: MainAdapter
    private lateinit var recyclerviewHome: RecyclerView
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPrefs = getSharedPreferences(SHARED_PREFS_SETTING, Context.MODE_PRIVATE)
        setTheme(getThemeInfo(sharedPrefs.getString(SHARED_PREFS_THEME, DARK_MODE)))
        setContentView(R.layout.main_screen)
        mainViewModel = ViewModelProviders.of(this).get(MainScreenViewModel::class.java)

        initRecyclerView()
        setupView()
        setupObserver()
    }


    private fun initRecyclerView() {
        mainAdapter = MainAdapter()
        recyclerviewHome = findViewById(R.id.recyclerView_home)
        recyclerviewHome.adapter = mainAdapter
        recyclerviewHome.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun setupView() {
        myTodayDate = findViewById(R.id.tv_title)
        addButton = findViewById(R.id.btn_add_new)
        calendarButton = findViewById(R.id.btn_calendar)
        settingButton = findViewById(R.id.btn_setting)

        myTodayDate.text = getDate(FULLDATETIME)

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
            Log.i("ITEM: ", it.date_note.toString())
        }
    }

    private fun showError(error: Throwable) {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
    }
}