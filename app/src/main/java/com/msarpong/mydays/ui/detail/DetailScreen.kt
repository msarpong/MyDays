package com.msarpong.mydays.ui.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.calendar.CalendarScreen
import com.msarpong.mydays.ui.detailDate.DateEvent
import com.msarpong.mydays.ui.main.MainState
import com.msarpong.mydays.ui.setting.SettingScreen
import com.msarpong.mydays.utils.formatDateTime
import org.msarpong.mydays.Db.Notes
import java.text.SimpleDateFormat

private const val BUNDLE_ID: String = "BUNDLE_ID"
const val SHARED_PREFS_SETTING = "Settings_prefs"
const val SHARED_PREFS_THEME = "Theme"
const val DARK_MODE = "DARK"
const val LIGHT_MODE = "LIGHT"

class DetailScreen : AppCompatActivity() {

    private lateinit var sharedPrefs: SharedPreferences

    private lateinit var calendarButton: ImageButton
    private lateinit var settingButton: ImageButton

    companion object {
        fun openDetail(startingActivity: Activity, gifId: Int) {
            val intent = Intent(startingActivity, DetailScreen::class.java)
                .putExtra(BUNDLE_ID, gifId)
            startingActivity.startActivity(intent)
        }
    }

    private lateinit var detailViewModel: DetailScreenViewModel

    private lateinit var detailTitle: TextView
    private lateinit var detailBody: TextView
    private lateinit var detailDate: TextView
    private lateinit var detailMood: ImageView
    private var noteId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = getSharedPreferences(SHARED_PREFS_SETTING, Context.MODE_PRIVATE)
        val myTheme = sharedPrefs.getString(SHARED_PREFS_THEME, LIGHT_MODE)
        if (myTheme == DARK_MODE) {
            setTheme(R.style.DarkTheme);
        } else if (myTheme == LIGHT_MODE) {
            setTheme(R.style.LightTheme);
        }
        setContentView(R.layout.detail_screen)

        detailViewModel = ViewModelProviders.of(this).get(DetailScreenViewModel::class.java)
        setupViews()
        setupObserver()
    }


    private fun setupViews() {
        detailTitle = findViewById(R.id.detail_title)
        detailBody = findViewById(R.id.detail_body)
        detailMood = findViewById(R.id.detail_mood)
        detailDate = findViewById(R.id.detail_date)

        noteId = intent!!.getIntExtra(BUNDLE_ID, 1)

        detailViewModel.send(DetailEvent.Load, noteId)

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

    private fun setupObserver() {
        detailViewModel.state.observe(this, Observer { state ->
            when (state) {
                is DetailState.Error -> showError(state.error)
                is DetailState.Success -> showDatas(state.dayNotes)
            }
        })
    }

    private fun showDatas(dayNotes: Notes) {
        detailTitle.text = dayNotes.title
        detailBody.text = dayNotes.text
        detailDate.text = dayNotes.datetime.formatDateTime("yyyy-MM-dd HH:mm", "dd-M-yyyy HH:mm")
//        detailDate.text = convertDate(dayNotes.date_note)
//        detailDate.text = dayNotes.date_note

        var moodIcon = dayNotes.mood
        Log.d("MOOD", moodIcon)

        when (moodIcon) {
            "smile" -> detailMood.setImageResource(R.drawable.ic_smile)
            "sad" -> detailMood.setImageResource(R.drawable.ic_sad)
            "confused" -> detailMood.setImageResource(R.drawable.ic_confused)
        }
    }

    private fun showError(error: Throwable) {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
    }
}


