package com.msarpong.mydays.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MyDaysDao {

    //
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dayNote: MyDaysEntity)

    //      Get all row
    @Query("SELECT * FROM day_note ORDER BY created DESC")
    suspend fun getAllDay(): List<MyDaysEntity>

    //      Get one row
    @Query("SELECT * FROM day_note WHERE id = :id")
//    suspend fun getDayById(id: String): DayEntity
    suspend fun getDayById(id: String): List<MyDaysEntity>

    @Query("DELETE FROM day_note")
    suspend fun deleteAllDay()


}