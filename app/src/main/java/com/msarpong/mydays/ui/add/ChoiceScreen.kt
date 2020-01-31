package com.msarpong.mydays.ui.add

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProviders
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.add.text.AddTextScreen
import com.msarpong.mydays.ui.main.MainScreen


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
            val intent = Intent(this, AddTextScreen::class.java)
            startActivityForResult(intent, 1000)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
//                title = data.extras!!.getString("ADD_NOTE_TITLE")
//                Toast.makeText(this, title, Toast.LENGTH_LONG).show()

                choiceViewModel.send(

                )
            }

            returntToMain()
        }

    }

    private fun returntToMain() {
        val intent = Intent(this, MainScreen::class.java)
        startActivity(intent)
    }

//    private fun setupObserver() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
}
