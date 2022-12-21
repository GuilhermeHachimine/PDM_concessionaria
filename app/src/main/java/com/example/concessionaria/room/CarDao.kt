package com.example.concessionaria.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.concessionaria.CarType
import kotlinx.coroutines.flow.Flow


@Dao
interface CarDao {
    @Query("SELECT * FROM car")
    fun selectAll(): Flow<List<CarType>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(car: CarType)

    @Query("Delete From car WHERE uid = :id")
    suspend fun delete(id:Long)

    @Query("DELETE FROM car")
    suspend fun deleteAllCar()


    @Query("UPDATE car SET isSold = :isSold where uid = :uid")
    suspend fun updateCar(isSold:Boolean,uid:Long)
}