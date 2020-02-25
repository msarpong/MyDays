package com.msarpong.mydays.ui.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProviders
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.add.text.AddTextScreen
import com.msarpong.mydays.ui.calendar.CalendarScreen
import com.msarpong.mydays.ui.main.MainScreen
import com.msarpong.mydays.ui.setting.SettingScreen
import org.msarpong.mydays.Db.Notes
import kotlin.random.Random


class ChoiceScreen : AppCompatActivity() {

    private lateinit var choiceViewModel: ChoiceScreenViewModel
    private lateinit var goToAddText: CardView

    private lateinit var calendarButton: ImageButton
    private lateinit var settingButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        choiceViewModel = ViewModelProviders.of(this).get(ChoiceScreenViewModel::class.java)

        setContentView(R.layout.choice_screen)
        setupView()

    }

    private fun setupView() {

        goToAddText = findViewById(R.id.card_text)
        goToAddText.setOnClickListener {
            val intent = Intent(this, AddTextScreen::class.java)
            startActivityForResult(intent, 1000)
        }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            if (data != null) {

                choiceViewModel.send(
                    MyDaysEvent.AddNote(
                        Notes(
                            title = data.extras!!.getString("ADD_NOTE_TITLE").toString(),
                            type = data.extras!!.getString("ADD_NOTE_TYPE").toString(),
                            text = data.extras!!.getString("ADD_NOTE_TEXT").toString(),
                            mood = data.extras!!.getString("ADD_NOTE_MOOD").toString(),
                            image = data.extras!!.getString("ADD_NOTE_IMAGE").toString(),
                            date_note = data.extras!!.getString("ADD_NOTE_DATE").toString(),
                            hour_note = data.extras!!.getString("ADD_NOTE_HOUR").toString(),
                            datetime = data.extras!!.getString("ADD_NOTE_DATETIME").toString()
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
