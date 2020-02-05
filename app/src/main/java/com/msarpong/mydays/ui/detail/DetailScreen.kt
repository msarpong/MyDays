package com.msarpong.mydays.ui.detail

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.msarpong.mydays.R

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

    private lateinit var noteId: String
    private lateinit var text : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_screen)

        detailViewModel = ViewModelProviders.of(this).get(DetailScreenViewModel::class.java)
        noteId = intent.getStringExtra(BUNDLE_ID)

        setupViews()

    }

    private fun setupViews() {
        text = findViewById(R.id.textView)
        text.setText(noteId)
    }
}
