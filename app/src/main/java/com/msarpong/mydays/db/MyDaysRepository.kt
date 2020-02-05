package org.msarpong.mydays.Db

class MyDaysRepository(private val myDaysDao: MyDaysDao) {

    suspend fun insert(notes: Notes) {
        myDaysDao.insert(notes)
    }

    suspend fun getAllNotes() = myDaysDao.getAllNotes()

    suspend fun getNotesByDate(todayDate: String) = myDaysDao.getNotesByDate(todayDate)

    suspend fun deleteAll() = myDaysDao.deleteAllNotes()

}