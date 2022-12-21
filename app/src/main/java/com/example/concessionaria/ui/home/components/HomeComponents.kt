package com.example.concessionaria.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.concessionaria.room.CarType

@Composable
fun CarItem(
    car:CarType,
    onChecked:(Boolean)->Unit,
    onDelete:(CarType)->Unit,
    onNavigation:(CarType)->Unit,
){
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable { onNavigation(car) },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text=car.modelo,style = MaterialTheme.typography.subtitle1)
                Text(text=car.tipo,style = MaterialTheme.typography.subtitle1)
                if(car.isSold) {
                    Text(text="Vendido",style = MaterialTheme.typography.subtitle1)
                } else {
                    Text(text="à venda",style = MaterialTheme.typography.subtitle1)
                }

            }
            IconButton(onClick = {onChecked(true)}) {
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)
            }
        }
    }

}