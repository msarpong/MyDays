package com.msarpong.mydays.ui.add.text

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.msarpong.mydays.R

class AddTextScreen : AppCompatActivity() {

    private lateinit var saveBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_text_screen)

        setupView()
    }

    private fun setupView() {

    }
}
