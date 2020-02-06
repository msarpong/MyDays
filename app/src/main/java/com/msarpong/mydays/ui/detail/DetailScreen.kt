package com.msarpong.mydays.ui.detail

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    private lateinit var notesText: TextView
    private var noteId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_screen)

        detailViewModel = ViewModelProviders.of(this).get(DetailScreenViewModel::class.java)
        setupViews()
        setupObserver()
    }


    private fun setupViews() {
        notesText = findViewById(R.id.textView)
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
        notesText.text = dayNotes.type
    }

    private fun showError(error: Throwable) {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
    }
}
