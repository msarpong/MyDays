package com.msarpong.mydays.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProviders
import com.msarpong.mydays.R


class ChoiceScreen : AppCompatActivity() {

    private lateinit var choiceViewModel: ChoiceScreenViewModel
    private lateinit var goToAddText: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        choiceViewModel = ViewModelProviders.of(this).get(ChoiceScreenViewModel::class.java)

        setContentView(R.layout.choice_screen)

        setupView()
//
    }

    private fun setupView() {

        goToAddText = findViewById(R.id.add_text)
        goToAddText.setOnClickListener {
            Toast.makeText(this, "Go to text", Toast.LENGTH_LONG).show()
        }
    }

//    private fun setupObserver() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
}
