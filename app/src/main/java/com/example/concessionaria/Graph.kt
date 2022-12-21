package com.example.concessionaria


import android.content.Context
import com.example.concessionaria.room.CarDataSource
import com.example.concessionaria.room.CarDatabase

object Graph {
    lateinit var database: CarDatabase
        private set
    val carRepo by lazy {
        CarDataSource(database.carDao())
    }

    fun provide(context: Context) {
        database = CarDatabase.getDatabase(context)
    }

}