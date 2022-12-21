package com.example.concessionaria

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "car")
data class CarType(
    @PrimaryKey(autoGenerate = true)
    val uid :Long,
    val modelo: String,
    val tipo: String,
    val preco: Double,
    val isSold: Boolean = false,
)


