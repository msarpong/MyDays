package com.msarpong.mydays.utils

import androidx.appcompat.app.AppCompatDelegate
import com.msarpong.mydays.R

fun getThemeMode(): Int {
    return AppCompatDelegate.getDefaultNightMode()
}

fun getThemeInfo(theme: String?): Int {
    if (theme == DARK_MODE)
        return R.style.DarkTheme
    return R.style.LightTheme
}

