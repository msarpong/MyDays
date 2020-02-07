package com.msarpong.mydays.ui.detail

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.main.MainState
import org.msarpong.mydays.Db.Notes

private const val BUNDLE_ID: String = "BUNDLE_ID"

class DetailScreen : AppCompatActivity() {

    companion object {
        fun openDetail(startingActivity: Activity, gifId: String) {
            val intent = Intent(startingActivity, DetailScreen::class.java)
                .putExtra(BUNDLE_ID, gifId)
            startingActivity.startActivity(intent)
        }
    }

    private lateinit var detailViewModel: DetailScreenViewModel

    private lateinit var detailTitle: TextView
    private lateinit var detailBody: TextView
    private lateinit var detailCategory: TextView
    private lateinit var detailMood: ImageView
    private var noteId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_screen)

        detailViewModel = ViewModelProviders.of(this).get(DetailScreenViewModel::class.java)
        setupViews()
        setupObserver()
    }


    private fun setupViews() {
        detailTitle = findViewById(R.id.detail_title)
        detailBody = findViewById(R.id.detail_body)
        detailCategory = findViewById(R.id.detail_category)
        detailMood = findViewById(R.id.detail_mood)

        noteId = intent.getStringExtra(BUNDLE_ID).toInt()

        detailViewModel.send(DetailEvent.Load, noteId)
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
        detailCategory.text = dayNotes.type

        var moodIcon = dayNotes.mood
Log.d("MOOD", moodIcon)

        when(moodIcon){
             "smile" -> detailMood.setImageResource(R.drawable.ic_smile)
             "sad" -> detailMood.setImageResource(R.drawable.ic_sad)
             "confused" -> detailMood.setImageResource(R.drawable.ic_confused)
        }

    }

    private fun showError(error: Throwable) {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
    }
}
