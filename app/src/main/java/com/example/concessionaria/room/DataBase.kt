package com.example.concessionaria.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.concessionaria.CarType

@Database(entities = [CarType::class],version = 1, exportSchema = false)
abstract class CarDatabase : RoomDatabase() {
    abstract fun carDao():CarDao

    companion object {
        @Volatile
        private var INSTANCE: CarDatabase? = null
        fun getDatabase(context: Context): CarDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarDatabase::class.java,
                    "car_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

