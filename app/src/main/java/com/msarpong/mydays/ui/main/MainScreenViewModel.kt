package com.msarpong.mydays.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*

sealed class MainEvent {
    data class DateToday(val text: String) : MainEvent()
}

sealed class MainState {
    data class TextChanged(val text: String) : MainState()
}

class MainScreenViewModel(application: Application) : AndroidViewModel(application) {

    var state: MutableLiveData<MainState> = MutableLiveData()

    init {
    }




}
