package com.example.concessionaria.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.concessionaria.Graph
import com.example.concessionaria.room.CarDataSource
import com.example.concessionaria.room.CarType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class HomeViewModel(private val carDataSource: CarDataSource = Graph.carRepo):ViewModel() {
    private val _state = MutableStateFlow(HomeViewState())
    val state:StateFlow<HomeViewState>
    get() = _state

    val carList = carDataSource.selectAll
    val selected = MutableStateFlow(_state.value.selected)
    init {
        viewModelScope.launch {
            combine(carList,selected) {
                carList:List<CarType>,selected: Boolean ->
                HomeViewState(carList,selected)
            }.collect {
                _state.value = it
            }
        }
    }

    fun updateCar(selected: Boolean,id:Long) = viewModelScope.launch {
        carDataSource.updateCar(selected,id)
    }

    fun delete(car:CarType) = viewModelScope.launch {
        carDataSource.deleteCar(car)
    }
}

data class HomeViewState(
    val carList:List<CarType> = emptyList(),
    val selected: Boolean = false
)