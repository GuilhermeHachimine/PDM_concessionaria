package com.example.concessionaria.ui.home.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.concessionaria.Graph
import com.example.concessionaria.room.CarDataSource
import com.example.concessionaria.room.CarType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class DetailViewModel(
    private val carDataSource: CarDataSource= Graph.carRepo,
    private val id : Long
) : ViewModel(){
    private val carText = MutableStateFlow("")
    private val carTipo = MutableStateFlow("")
    private val carPreco = MutableStateFlow(0.0)
    private val selectedId = MutableStateFlow(-1L)

    private val _state = MutableStateFlow(DetailViewState())
    val state: StateFlow<DetailViewState>
        get() = _state

    init {
        viewModelScope.launch {
            combine(carText,carTipo,carPreco,selectedId) {text,tipo,preco,id ->
                DetailViewState(text,tipo,preco,id)
            }.collect {
                _state.value = it

            }
        }
    }

    init {
        viewModelScope.launch {
            carDataSource.selectAll.collect { car->
                car.find{
                    it.uid == selectedId.value
                }.also {
                    selectedId.value = it?.uid?:-1
                    if(selectedId.value != -1L) {
                        carText.value = it?.modelo?:""
                        carTipo.value = it?.tipo?:""
                        carPreco.value = it?.preco?:0.0

                    }
                }

            }
        }
    }

    fun onTextChange(newModel:String) {
        carText.value = newModel
    }

    fun onModelChange(newType:String) {
        carTipo.value = newType
    }

    fun onPriceChange(newPrice:Double) {
        carPreco.value = newPrice
    }

    fun insert(car:CarType) = viewModelScope.launch {
        carDataSource.insertCar(car)
    }

}

data class DetailViewState(
    val car:String = "",
    val tipo:String = "",
    val preco:Double = 0.0,
    val selectedId:Long=-1L,
)


@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val id :Long): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(id=id) as T
        } else {
            throw IllegalArgumentException("view model class desconhecida")
        }
    }
}