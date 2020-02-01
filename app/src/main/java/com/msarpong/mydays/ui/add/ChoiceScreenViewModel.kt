package com.msarpong.mydays.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.msarpong.mydays.Db.MyDaysRepository
import org.msarpong.mydays.Db.MyDaysRoomDatabase
import org.msarpong.mydays.Db.Notes


sealed class MyDaysEvent {
    object Load : MyDaysEvent()
    data class AddNote(val dayNotes: Notes) : MyDaysEvent()
}

// States that HomeViewModel can have
sealed class MyDaysState {

    data class Error(val error: Throwable) : MyDaysState()
    data class Success(val dayNotes: List<Notes>) : MyDaysState()
}

class ChoiceScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MyDaysRepository
    private val notesData = MutableLiveData<List<Notes>>()
    var state: MutableLiveData<MyDaysState> = MutableLiveData()

    init {
        val dao = MyDaysRoomDatabase.getDatabase(application).mydaysDao()
        repository = MyDaysRepository(dao)
        updateNote()
    }


    private fun updateNote() = viewModelScope.launch {
        notesData.postValue(repository.getAllNotes())
        state.value = MyDaysState.Success(repository.getAllNotes())
    }

    private fun insert(note: Notes) = viewModelScope.launch {
        repository.insert(note)
        updateNote()
    }

    fun send(event: MyDaysEvent) {
        when (event) {
            is MyDaysEvent.Load -> loadContent()
            is MyDaysEvent.AddNote -> {
                insert(note = event.dayNotes)
            }
        }
    }

    private fun loadContent() {
        state.value = MyDaysState.Success(notesData.value!!.toList())
    }

}