package com.tasbeeh.counter.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tasbeeh.counter.domain.model.SavedCount

@Database(entities = [SavedCount::class], version = 1, exportSchema = false)
abstract class TasbeehDatabase : RoomDatabase() {

    abstract fun savedCountDao(): SavedCountDao

    companion object {
        @Volatile
        private var INSTANCE: TasbeehDatabase? = null

        fun getDatabase(context: Context): TasbeehDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TasbeehDatabase::class.java,
                    "tasbeeh_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
