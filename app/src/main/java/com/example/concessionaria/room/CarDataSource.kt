package com.example.concessionaria.room

import com.example.concessionaria.CarType
import kotlinx.coroutines.Dispatchers

class CarDataSource(private val carDao: CarDao) {
    val selectAll = carDao.selectAll()

    suspend fun insertCar(carType: CarType) {
        Dispatchers.IO.apply {
            carDao.insert(carType)
        }
    }

    suspend fun deleteCar(carType: CarType) {
        Dispatchers.IO.apply {
            carDao.delete(carType.uid)
        }
    }

    suspend fun updateCar(isSold:Boolean,id:Long) {
        Dispatchers.IO.apply {
            carDao.delete(carType.uid)
        }
    }
}