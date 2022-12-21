package com.example.concessionaria.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.concessionaria.room.CarType
import com.example.concessionaria.ui.home.components.CarItem


@Composable
fun HomeScreen(onNavigate:(CarType?) -> Unit){
    val viewModel = viewModel(HomeViewModel::class.java)
    val state by viewModel.state.collectAsState()

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onNavigate(null)}) {
            Icon(imageVector = Icons.Default.Home, contentDescription = null)
        }
    }) {
        LazyColumn() {
            items(state.carList) { car->
                CarItem(car = car,
                    onChecked = { viewModel.updateCar(it,car.uid)} ,
                    onDelete={viewModel.delete(it)},
                onNavigation= {onNavigate(it)}
                )
            }
        }
    }
}