package com.msarpong.mydays.db

class MyDaysRepository(private val dayDao: MyDaysDao) {

    suspend fun insert(dayEntity: MyDaysEntity) {
        dayDao.insert(dayEntity)
    }

    suspend fun getDayById(id: String) = dayDao.getDayById(id)

    suspend fun getAll() = dayDao.getAllDay()

    suspend fun deleteAll() = dayDao.deleteAllDay()

}