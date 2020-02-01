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
import org.msarpong.mydays.Db.Notes
import java.text.SimpleDateFormat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.util.*

class MainScreen : AppCompatActivity() {

    private lateinit var mainViewModel: MainScreenViewModel
    private lateinit var myTodayDate: TextView
    private lateinit var addButton: Button
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

        addButton.setOnClickListener {
            val intent = Intent(this, ChoiceScreen::class.java)
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

    private fun showDatas(moneyTransactions: List<Notes>) {
        mainAdapter.submitList(moneyTransactions)
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
