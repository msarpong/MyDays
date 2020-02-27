package com.msarpong.mydays.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.main.MainScreen


fun getThemeMode(): Int {
    return AppCompatDelegate.getDefaultNightMode()
}

fun getThemeInfo(): Int {
    val theme: Int = R.style.LightTheme
    if (getThemeMode() == AppCompatDelegate.MODE_NIGHT_YES) {
        val theme = R.style.DarkTheme
    } else {
        val theme = R.style.LightTheme
    }
    return theme
}