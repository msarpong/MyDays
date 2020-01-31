package com.msarpong.mydays.ui.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.add.ChoiceScreen
import java.text.SimpleDateFormat
import java.util.*

class MainScreen : AppCompatActivity() {

    private lateinit var mainViewModel: MainScreenViewModel
    private lateinit var myTodayDate: TextView
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(this).get(MainScreenViewModel::class.java)

        setContentView(R.layout.main_screen)

        setupView()
        setupObserver()
    }

    private fun setupView() {
        myTodayDate = findViewById(R.id.tv_title)
        myTodayDate.setText(dateToday())
        addButton = findViewById(R.id.btn_add_new)

        addButton.setOnClickListener {
            val intent = Intent(this, ChoiceScreen::class.java)
            startActivity(intent)
        }
    }

    private fun setupObserver() {

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
