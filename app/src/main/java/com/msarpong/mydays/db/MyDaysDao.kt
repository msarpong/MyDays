package org.msarpong.mydays.Db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#4
@Dao
interface MyDaysDao {

    @Query("SELECT * from mydiary_notes")
    suspend fun getAllNotes(): List<Notes>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Notes)

    @Query("DELETE FROM mydiary_notes")
    suspend fun deleteAllNotes()


}