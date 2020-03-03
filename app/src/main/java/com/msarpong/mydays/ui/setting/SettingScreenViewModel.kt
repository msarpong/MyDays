package com.msarpong.mydays.ui.setting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import org.msarpong.mydays.Db.MyDaysRepository
import org.msarpong.mydays.Db.MyDaysRoomDatabase
import org.msarpong.mydays.Db.Notes


sealed class SettingEvent {
    object Load : SettingEvent()

}

sealed class SettingState {

}

class SettingScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MyDaysRepository
    private val notesData = MutableLiveData<List<Notes>>()
    var state: MutableLiveData<SettingState> = MutableLiveData()

    init {
        val dao = MyDaysRoomDatabase.getDatabase(application).mydaysDao()
        repository = MyDaysRepository(dao)
    }
}