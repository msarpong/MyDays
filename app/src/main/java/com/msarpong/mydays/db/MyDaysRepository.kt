package org.msarpong.mydays.Db

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class MyDaysRepository(private val myDaysDao: MyDaysDao) {

//    val allWords: List<Word> = wordDao.getAlphabetizedWords()

    suspend fun insert(notes: Notes) {
        myDaysDao.insert(notes)
    }

    suspend fun getAllNotes() = myDaysDao.getAllNotes()
}