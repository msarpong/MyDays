package org.msarpong.mydays.Db

class MyDaysRepository(private val myDaysDao: MyDaysDao) {

    suspend fun insert(notes: Notes) {
        myDaysDao.insert(notes)
    }

    suspend fun getAllNotes() = myDaysDao.getAllNotes()

    suspend fun getNoteById(id: Int) = myDaysDao.getNoteById(id)

    suspend fun deleteAll() = myDaysDao.deleteAllNotes()

    suspend fun getNoteByDate(datetime: String) = myDaysDao.getNoteByDate(datetime)


}