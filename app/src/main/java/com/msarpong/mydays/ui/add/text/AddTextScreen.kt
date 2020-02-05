package com.msarpong.mydays.ui.add.text

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.main.MainScreen
import java.text.SimpleDateFormat
import java.util.*

class AddTextScreen : AppCompatActivity() {

    private lateinit var saveBtn: Button
    private lateinit var titleET: EditText
    private lateinit var bodyET: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_text_screen)
        setupView()
    }

    private fun setupView() {

        saveBtn = findViewById(R.id.btn_save)
        saveBtn.setOnClickListener {

            val title = findViewById<EditText>(R.id.editTitle).text.toString()
            val body = findViewById<EditText>(R.id.editBody).text.toString()
            val dateNote = dateToday()

            intent.putExtra("ADD_NOTE_TITLE", title)
            intent.putExtra("ADD_NOTE_BODY", body)
            intent.putExtra("ADD_NOTE_DATE", dateNote)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun dateToday(): String {
        val pattern = "dd/M/yyyy hh:mm"
        Locale.setDefault(Locale.ITALIAN)
        val current = SimpleDateFormat(pattern)
        val today = current.format(Date())
        return today
    }
}
