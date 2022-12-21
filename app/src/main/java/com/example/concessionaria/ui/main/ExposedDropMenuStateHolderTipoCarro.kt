package com.example.concessionaria.ui.main

import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import com.example.concessionaria.ExposedDropDownMenuType
import com.example.concessionaria.R

class ExposedDropMenuStateHolderTipoCarro {

    var enabled by mutableStateOf(false)
    var value by mutableStateOf("")
    var selectedIndex by mutableStateOf(-1)
    var size by mutableStateOf(Size.Zero)
    //val icon: Int


    // Lista dos itens no menu
    val items = (1..5).map{
        "option $it"
    }
    fun onEnabled(newValue: Boolean){
        enabled = newValue
    }

    // funcao pra pegar/pegar do banco de dados
    fun onSelectedIndex(newValue: Int){
        selectedIndex = newValue
        value = items[selectedIndex]
    }
    fun onSize(newValue: Size){
        size = newValue
    }

}

@Composable
fun rememberExposedDropMenuStateHolderTipoCarro() = remember{
    ExposedDropMenuStateHolderTipoCarro()

}