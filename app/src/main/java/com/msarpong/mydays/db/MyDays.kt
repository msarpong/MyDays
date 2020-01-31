package org.msarpong.mydays.Db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mydiary_notes")
data class Notes(
    @PrimaryKey
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "mood") val date: String,
    @ColumnInfo(name = "datetime") val date: String
)
//
//@Entity(tableName = "mydiary_users")
//data class Users(
//    @PrimaryKey(autoGenerate = true) val id: Int,
//    @ColumnInfo(name = "name") val title:String,
//    @ColumnInfo(name = "email") val type:String
//
//)
//data class DiaryNote(@PrimaryKey @ColumnInfo(name = "word") val word: String)

