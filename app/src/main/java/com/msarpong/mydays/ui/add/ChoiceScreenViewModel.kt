package com.msarpong.mydays.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel


sealed class MyDaysEvent {
    object Load : MyDaysEvent()
    data class AddNote(val dayNotes: MoneyTransaction) : MyDaysEvent()
}

// States that HomeViewModel can have
sealed class MyDaysState {

    data class Error(val error: Throwable) : MyDaysState()
    data class Success(val dayNotes: List<MoneyTransaction>) : MyDaysState()
}


class ChoiceScreenViewModel(application: Application) : AndroidViewModel(application) {
 private val repository:
}