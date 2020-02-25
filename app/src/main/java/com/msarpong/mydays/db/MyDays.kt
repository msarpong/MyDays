package org.msarpong.mydays.Db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mydiary_notes")
data class Notes(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "mood") val mood: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "date_note") val date_note: String,
    @ColumnInfo(name = "hour_note") val hour_note: String,
    @ColumnInfo(name = "datetime") val datetime: String

)

