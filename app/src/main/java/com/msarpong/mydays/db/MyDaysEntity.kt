package com.msarpong.mydays.db


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "day_note")
data class MyDaysEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title")     var title: String,
    @ColumnInfo(name = "body")      var body: String,
    @ColumnInfo(name = "created")   var created: String,
    @ColumnInfo(name = "category")  var category: String,
    @ColumnInfo(name = "mood")      var mood: String
)