package com.msarpong.mydays.ui.add.read

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.msarpong.mydays.R
import com.msarpong.mydays.utils.DARK_MODE
import com.msarpong.mydays.utils.SHARED_PREFS_SETTING
import com.msarpong.mydays.utils.SHARED_PREFS_THEME


import com.msarpong.mydays.utils.getThemeInfo

private lateinit var sharedPrefs: SharedPreferences

class AddReadScreen : AppCompatActivity() {

    companion object {
        fun OpenAddRead(startingActivity: Activity) {
            val intent = Intent(startingActivity, AddReadScreen::class.java)
            startingActivity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = getSharedPreferences(SHARED_PREFS_SETTING, Context.MODE_PRIVATE)
        setTheme(getThemeInfo(sharedPrefs.getString(SHARED_PREFS_THEME, DARK_MODE)))
        setContentView(R.layout.add_read_screen)
    }
}
