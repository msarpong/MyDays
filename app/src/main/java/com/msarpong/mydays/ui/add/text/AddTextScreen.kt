package com.msarpong.mydays.ui.add.text

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.calendar.CalendarScreen
import com.msarpong.mydays.ui.setting.SettingScreen
import java.text.SimpleDateFormat
import java.util.*


class AddTextScreen : AppCompatActivity() {

    private lateinit var saveBtn: Button

    private lateinit var calendarButton: ImageButton
    private lateinit var settingButton: ImageButton

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

            calendarButton = findViewById(R.id.btn_calendar)
            settingButton = findViewById(R.id.btn_setting)


            calendarButton.setOnClickListener {
                val intent = Intent(this, CalendarScreen::class.java)
                startActivity(intent)
            }

            settingButton.setOnClickListener {
                val intent = Intent(this, SettingScreen::class.java)
                startActivity(intent)
            }

            var selectedId = moodRB.checkedRadioButtonId
            var mood = "default"

            var confused = R.id.mood_confused
            var sad = R.id.mood_sad
            var smile = R.id.mood_smile

            when (selectedId) {
                confused -> mood = "sad"
                sad -> mood = "sad"
                smile -> mood = "smile"
            }

            intent.putExtra("ADD_NOTE_TITLE", titleET)
            intent.putExtra("ADD_NOTE_TYPE", "text")
            intent.putExtra("ADD_NOTE_TEXT", bodyET)
            intent.putExtra("ADD_NOTE_MOOD", mood)
            intent.putExtra("ADD_NOTE_IMAGE", "image")
            intent.putExtra("ADD_NOTE_DATE", getDate("dd/M/yyyy"))
            intent.putExtra("ADD_NOTE_HOUR", getDate("hh:mm"))

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun getDate(pattern: String): String {
        Locale.setDefault(Locale.ITALIAN)
        val current = SimpleDateFormat(pattern)
        val todayDate = current.format(Date())
        return todayDate
    }



}
