package com.example.concessionaria

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.concessionaria.ui.theme.ConcessionariaTheme


@Composable
fun MainScreen(){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var textModel by remember {
            mutableStateOf("Model")
        }
        TextField(value = textModel, onValueChange = {
                newTextModel -> textModel = newTextModel

        })
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    ConcessionariaTheme {
        MainScreen()
    }
}