package org.msarpong.mydays.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Notes::class), version = 1, exportSchema = false)

public abstract class MyDaysRoomDatabase : RoomDatabase() {

    abstract fun mydaysDao(): MyDaysDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MyDaysRoomDatabase? = null

        fun getDatabase(context: Context): MyDaysRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDaysRoomDatabase::class.java,
                    "mydays_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}



