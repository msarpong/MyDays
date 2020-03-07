package com.msarpong.mydays.ui.detailDate

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.msarpong.mydays.Db.MyDaysRepository
import org.msarpong.mydays.Db.MyDaysRoomDatabase
import org.msarpong.mydays.Db.Notes

sealed class DateEvent {
    object Load : DateEvent()
}

sealed class DateState {
    data class Error(val error: Throwable) : DateState()
    data class Success(val dayNotes: List<Notes>) : DateState()
}

class DateScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MyDaysRepository
    var state: MutableLiveData<DateState> = MutableLiveData()

    init {
        val dao = MyDaysRoomDatabase.getDatabase(application).mydaysDao()
        repository = MyDaysRepository(dao)
    }

    fun send(event: DateEvent, dateId: String) {
        when (event) {
            is DateEvent.Load -> {
                loadContent(dateId)
            }
        }
    }

    private fun loadContent(dateId: String) = viewModelScope.launch {
        if (repository.getNoteByDate(dateId).isNotEmpty()) {
            state.value = DateState.Success(repository.getNoteByDate(dateId))
        } else {
            state.value = DateState.Error(Throwable("Error: No data found"))
        }
    }
}