package com.msarpong.mydays.ui.add.text

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.main.MainScreen
import java.text.SimpleDateFormat
import java.util.*

class AddTextScreen : AppCompatActivity() {

    private lateinit var saveBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_text_screen)
        setupView()
    }

    private fun setupView() {

        saveBtn = findViewById(R.id.btn_save)
        saveBtn.setOnClickListener {
            val titleET = findViewById<EditText>(R.id.editTitle).text.toString()
            val bodyET = findViewById<EditText>(R.id.editBody).text.toString()
            val moodRB = findViewById<RadioGroup>(R.id.radio_mood)

            var selectedId = moodRB.checkedRadioButtonId
//            var radioButton = findViewById<RadioButton>(selectedId)

            val dateNote = dateToday()
            var mood = "default"

            var confused = R.id.mood_confused
            var sad = R.id.mood_sad
            var smile = R.id.mood_smile

            when(selectedId){
                confused -> mood = "sad"
                sad -> mood = "sad"
                smile -> mood = "smile"
            }

            intent.putExtra("ADD_NOTE_TITLE", titleET)
            intent.putExtra("ADD_NOTE_TYPE", "text")
            intent.putExtra("ADD_NOTE_TEXT", bodyET)
            intent.putExtra("ADD_NOTE_MOOD", mood)
            intent.putExtra("ADD_NOTE_IMAGE", "image")
            intent.putExtra("ADD_NOTE_DATETIME", dateNote)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun dateToday(): String {
        val pattern = "dd-M-yyyy hh:mm:ss"
        Locale.setDefault(Locale.ITALIAN)
        val current = SimpleDateFormat(pattern)
        val today = current.format(Date())
        return today
    }


}
