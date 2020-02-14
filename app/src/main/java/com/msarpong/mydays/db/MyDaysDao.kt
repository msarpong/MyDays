package org.msarpong.mydays.Db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#4
@Dao
interface MyDaysDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Notes)

    @Query("SELECT * from mydiary_notes ORDER by id DESC")
    suspend fun getAllNotes(): List<Notes>

    @Query("SELECT * from mydiary_notes WHERE id =:noteId")
    suspend fun getNoteById(noteId: Int): Notes

    @Query("SELECT * from mydiary_notes WHERE date_note LIKE :datetime ORDER BY id DESC")
    suspend fun getNoteByDate(datetime : String): List<Notes>

    @Query("DELETE FROM mydiary_notes")
    suspend fun deleteAllNotes()

    @Query("DELETE FROM mydiary_notes WHERE id =:id")
    suspend fun deleteAllNotesById(id: String)
}