package com.msarpong.mydays.ui.add.text

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.calendar.CalendarScreen
import com.msarpong.mydays.ui.setting.SettingScreen
import com.msarpong.mydays.utils.getDate

const val SHARED_PREFS_SETTING = "Settings_prefs"
const val SHARED_PREFS_THEME = "Theme"
const val DARK_MODE = "DARK"
const val LIGHT_MODE = "LIGHT"

class AddTextScreen : AppCompatActivity() {

    private lateinit var saveBtn: Button
    private lateinit var calendarButton: ImageButton
    private lateinit var settingButton: ImageButton

    private lateinit var sharedPrefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPrefs = getSharedPreferences(SHARED_PREFS_SETTING, Context.MODE_PRIVATE)
        val myTheme = sharedPrefs.getString(SHARED_PREFS_THEME, LIGHT_MODE)
        if (myTheme == DARK_MODE) {
            setTheme(R.style.DarkTheme);
        } else if (myTheme == LIGHT_MODE) {
            setTheme(R.style.LightTheme);
        }

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
            intent.putExtra("ADD_NOTE_DATETIME", getDate("yyyy-MM-dd HH:mm"))

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }


}
