package com.msarpong.mydays.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.msarpong.mydays.Db.MyDaysRepository
import org.msarpong.mydays.Db.MyDaysRoomDatabase
import org.msarpong.mydays.Db.Notes
import java.text.SimpleDateFormat
import java.util.*

sealed class MainEvent {
    object Load : MainEvent()
}

sealed class MainState {
    data class Error(val error: Throwable) : MainState()
    data class Success(val dayNotes: List<Notes>) : MainState()
}

class MainScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MyDaysRepository
    private val notesData = MutableLiveData<List<Notes>>()
    var state: MutableLiveData<MainState> = MutableLiveData()

    init {
        val dao = MyDaysRoomDatabase.getDatabase(application).mydaysDao()
        repository = MyDaysRepository(dao)
        updateNote()
        dateToday()
    }

    private fun updateNote() = viewModelScope.launch {
        notesData.postValue(repository.getAllNotes())
        state.value = MainState.Success(repository.getNoteByDate(dateToday()))
    }

    fun send(event: MainEvent) {
        when (event) {
            is MainEvent.Load -> loadContent()
        }
    }

    private fun loadContent() {
        state.value = MainState.Success(notesData.value!!.toList())
    }

    private fun dateToday(): String {
        val pattern = "dd-M-yyyy"
        Locale.setDefault(Locale.ITALIAN)
        val current = SimpleDateFormat(pattern)
        val today = current.format(Date())
        Log.d("DATETIME", today)
        return today
    }

}
