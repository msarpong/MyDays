package com.msarpong.mydays.ui.add.read


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.calendar.CalendarScreen
import com.msarpong.mydays.ui.setting.SettingScreen
import com.msarpong.mydays.utils.*


class AddReadScreen : AppCompatActivity() {

    private lateinit var saveBtn: Button
    private lateinit var calendarButton: ImageButton
    private lateinit var settingButton: ImageButton
    private lateinit var sharedPrefs: SharedPreferences

    companion object {
        fun OpenAddRead(startingActivity: Activity) {
            val intent = Intent(startingActivity, AddReadScreen::class.java)
            startingActivity.startActivityForResult(intent, 1000)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = getSharedPreferences(SHARED_PREFS_SETTING, Context.MODE_PRIVATE)
        setTheme(getThemeInfo(sharedPrefs.getString(SHARED_PREFS_THEME, DARK_MODE)))
        setContentView(R.layout.add_read_screen)

        setupView()
        saveData()
    }

    private fun setupView() {
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
    }

    private fun saveData() {

        saveBtn = findViewById(R.id.btn_save)
        saveBtn.setOnClickListener {
            val title = findViewById<EditText>(R.id.editTitle).text.toString()
            val readFrom = findViewById<EditText>(R.id.read_page_from).text.toString()
            val readTo = findViewById<EditText>(R.id.read_page_to).text.toString()

            intent.putExtra("ADD_NOTE_TITLE", title)
            intent.putExtra("ADD_NOTE_TYPE", "read")
            intent.putExtra("ADD_NOTE_TEXT", "$readFrom|$readTo")
            intent.putExtra("ADD_NOTE_MOOD", "")
            intent.putExtra("ADD_NOTE_IMAGE", "image")
            intent.putExtra("ADD_NOTE_DATE", getDate(DATE))
            intent.putExtra("ADD_NOTE_HOUR", getDate(HOUR))
            intent.putExtra("ADD_NOTE_DATETIME", getDate(DATETIME))

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
