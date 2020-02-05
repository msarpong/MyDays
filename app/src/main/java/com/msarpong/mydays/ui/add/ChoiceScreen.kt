package com.msarpong.mydays.ui.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.add.text.AddTextScreen
import com.msarpong.mydays.ui.main.MainScreen
import org.msarpong.mydays.Db.Notes
import kotlin.random.Random


class ChoiceScreen : AppCompatActivity() {

    private lateinit var choiceViewModel: ChoiceScreenViewModel
    private lateinit var goToAddText: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        choiceViewModel = ViewModelProviders.of(this).get(ChoiceScreenViewModel::class.java)

        setContentView(R.layout.choice_screen)
        setupView()

    }

    private fun setupView() {

        goToAddText = findViewById(R.id.btn_to_text)
        goToAddText.setOnClickListener {
            val intent = Intent(this, AddTextScreen::class.java)
            startActivityForResult(intent, 1000)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            if (data != null) {

                val pref =
                    applicationContext.getSharedPreferences("MyPref", 0) // 0 - for private mode
                val editor = pref.edit()

                var oldId = pref.getInt("key_name", 0) // getting Integer
                editor.putInt("key_name", oldId + 1).apply() // Storing integer
                var newId = pref.getInt("key_name", 0) // getting Integer

                choiceViewModel.send(
                    MyDaysEvent.AddNote(
                        Notes(
                            id = newId.toString(),
                            title = data.extras!!.getString("ADD_NOTE_TITLE").toString(),
                            type = "TEXT",
                            text = data.extras!!.getString("ADD_NOTE_TEXT").toString(),
                            mood = Random.nextInt().toString(),
                            image = Random.nextInt().toString(),
                            datetime = data.extras!!.getString("ADD_NOTE_DATE").toString()
                        )
                    )
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
