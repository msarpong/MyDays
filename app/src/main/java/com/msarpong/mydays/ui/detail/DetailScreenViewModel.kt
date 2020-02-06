package com.msarpong.mydays.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.msarpong.mydays.ui.main.MainState
import kotlinx.coroutines.launch
import org.msarpong.mydays.Db.MyDaysRepository
import org.msarpong.mydays.Db.MyDaysRoomDatabase
import org.msarpong.mydays.Db.Notes

sealed class DetailEvent {
    object Load : DetailEvent()
}

sealed class DetailState {
    data class Error(val error: Throwable) : DetailState()
    data class Success(val dayNotes: Notes) : DetailState()
}

class DetailScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MyDaysRepository
    private val notesData = MutableLiveData<Notes>()
    var state: MutableLiveData<DetailState> = MutableLiveData()

    init {
        val dao = MyDaysRoomDatabase.getDatabase(application).mydaysDao()
        repository = MyDaysRepository(dao)
    }

    private fun getOneNote(id: Int) = viewModelScope.launch {
        notesData.postValue(repository.getNoteById(id))
        state.value = DetailState.Success(repository.getNoteById(id))
    }

    fun send(event: DetailEvent, id: Int) {
        when (event) {
            is DetailEvent.Load -> loadContent(id)
        }
    }

    private fun loadContent(id: Int) = viewModelScope.launch {
        state.value = DetailState.Success(repository.getNoteById(id))
    }
}