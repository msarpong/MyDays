package com.msarpong.mydays.ui.add

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.calendar.CalendarScreen
import com.msarpong.mydays.ui.main.MainScreen
import com.msarpong.mydays.ui.setting.SettingScreen
import com.msarpong.mydays.utils.*
import org.msarpong.mydays.Db.Notes

class ChoiceScreen : AppCompatActivity() {

    private lateinit var choiceViewModel: ChoiceScreenViewModel

    private lateinit var calendarButton: ImageButton
    private lateinit var settingButton: ImageButton

    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var choiceAdapter: ChoiceScreenAdapter
    private lateinit var recyclerViewChoice: RecyclerView

    private val cardList = listOf(
        Choice(R.drawable.ic_format_align, "Text"),
        Choice(R.drawable.ic_icon_awesome_running, "Sport"),
        Choice(R.drawable.ic_icon_awesome_running, "Read")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPrefs = getSharedPreferences(SHARED_PREFS_SETTING, Context.MODE_PRIVATE)
        setTheme(getThemeInfo(sharedPrefs.getString(SHARED_PREFS_THEME, DARK_MODE)))

        choiceViewModel = ViewModelProviders.of(this).get(ChoiceScreenViewModel::class.java)

        setContentView(R.layout.choice_screen)
        initRecyclerView()
        setupView()

    }

    private fun initRecyclerView() {
        choiceAdapter = ChoiceScreenAdapter()
        recyclerViewChoice = findViewById(R.id.recyclerView_choice)
        recyclerViewChoice.adapter = choiceAdapter
        recyclerViewChoice.layoutManager = GridLayoutManager(this, 3)
        choiceAdapter.submitList(cardList)
    }

    private fun setupView() {

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
        if (requestCode == ADD_TEXT && resultCode == Activity.RESULT_OK) {
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
            returnToMain()
        }
    }

    private fun returnToMain() {
        val intent = Intent(this, MainScreen::class.java)
        startActivity(intent)
    }

}
