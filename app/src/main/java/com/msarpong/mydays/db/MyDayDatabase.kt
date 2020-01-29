package com.msarpong.mydays.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(MyDaysEntity::class), version = 1, exportSchema = false)
public abstract class MyDaysDatabase : RoomDatabase() {

    abstract fun dayDao() : MyDaysDao

    companion object {
        @Volatile
        private var INSTANCE: MyDaysDatabase? = null

        fun getDatabase(context: Context): MyDaysDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDaysDatabase::class.java,
                    "day_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}