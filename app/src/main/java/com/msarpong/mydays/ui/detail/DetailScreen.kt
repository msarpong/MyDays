package com.msarpong.mydays.ui.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.calendar.CalendarScreen
import com.msarpong.mydays.ui.setting.SettingScreen
import com.msarpong.mydays.utils.*
import kotlinx.android.synthetic.main.detail_screen.*
import org.msarpong.mydays.Db.Notes

private const val BUNDLE_ID: String = "BUNDLE_ID"

class DetailScreen : AppCompatActivity() {

    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var calendarButton: ImageButton
    private lateinit var settingButton: ImageButton
    private lateinit var detailViewModel: DetailScreenViewModel
    private lateinit var detailTitle: TextView
    private lateinit var detailBody: TextView
    private lateinit var detailDate: TextView
    private lateinit var detailMood: ImageView
    private var noteId = 0

    companion object {
        fun openDetail(startingActivity: Activity, gifId: Int) {
            val intent = Intent(startingActivity, DetailScreen::class.java)
                .putExtra(BUNDLE_ID, gifId)
            startingActivity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = getSharedPreferences(SHARED_PREFS_SETTING, Context.MODE_PRIVATE)
        setTheme(getThemeInfo(sharedPrefs.getString(SHARED_PREFS_THEME, DARK_MODE)))
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

        cardView.setOnLongClickListener {

            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.bottom_sheet, null)
//            sharedPrefs = getSharedPreferences(SHARED_PREFS_SETTING, Context.MODE_PRIVATE)
//            setTheme(getThemeInfo(sharedPrefs.getString(SHARED_PREFS_THEME, DARK_MODE)))
            dialog.setContentView(view)
            dialog.show()
            Toast.makeText(this,"LONG", Toast.LENGTH_LONG).show()
            true
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
        detailDate.text = dayNotes.datetime.formatDateTime(DATETIME, FULLDATETIME)

        when (dayNotes.mood) {
            MOOD_SMILE -> detailMood.setImageResource(R.drawable.ic_smile)
            MOOD_SAD -> detailMood.setImageResource(R.drawable.ic_sad)
            MOOD_CONFUSED -> detailMood.setImageResource(R.drawable.ic_confused)
        }
    }

    private fun showError(error: Throwable) {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
    }
}


