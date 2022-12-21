package com.example.concessionaria



import android.app.Application

class CarApplication:Application(){
    override fun onCreate(){
        super.onCreate()
        Graph.provide(this)
    }
}