package org.msarpong.mydays.Db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mydiary_notes")
data class Notes(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "mood") val mood: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "datetime") val datetime: String
)

