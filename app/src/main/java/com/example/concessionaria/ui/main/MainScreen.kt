package com.example.concessionaria

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize
import com.example.concessionaria.ui.main.ExposedDropMenuStateHolderTipoCarro
import com.example.concessionaria.ui.main.rememberExposedDropMenuStateHolderTipoCarro
import com.example.concessionaria.ui.theme.ConcessionariaTheme

@Composable
fun MainScreen(){

    MainFields()

}

@Composable
fun MainFields(){

    val stateHolderTipoCarro = rememberExposedDropMenuStateHolderTipoCarro()

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        textFieldModel()

        ExposedDropDownMenuType(stateHolder = stateHolderTipoCarro)

        textFieldPrice()

    }

}

@Composable
fun textFieldModel(){
    //Modelo TEXT FIELD
    var textModel by remember {
        mutableStateOf("")
    }
    OutlinedTextField(value = textModel, onValueChange = {
            newTextModel -> textModel = newTextModel

    },
        label = { Text(text = "Model") },
        singleLine = true,
    )

}


@Composable
fun ExposedDropDownMenuType(stateHolder: ExposedDropMenuStateHolderTipoCarro){
    Column() {
        Box{
            OutlinedTextField(
                value = stateHolder.value,
                onValueChange = {},
                label = { Text(text = "Choose a type...") },
                trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Show Types",
                            Modifier.clickable{
                                stateHolder.onEnabled(!(stateHolder.enabled))
                            }
                        )
                },
                modifier = Modifier.onGloballyPositioned {
                    stateHolder.onSize(it.size.toSize())
                }
                )//OutlinedTextField
                DropdownMenu(
                    expanded = stateHolder.enabled,
                    onDismissRequest = {
                        stateHolder.onEnabled(false)
                },
                    modifier = Modifier.width(with(LocalDensity.current){stateHolder.size.width.toDp()})

                ){
                    stateHolder.items.forEachIndexed { index,
                                                       s -> DropdownMenuItem(
                        onClick = {
                            stateHolder.onSelectedIndex(index)
                            stateHolder.onEnabled(false)
                        }){
                            Text(text = s)
                        }// end DropdownMenuItem

                    }
                }

        }//end box
    }//end collum

}

@Composable
fun textFieldPrice(){
    //Preço TEXT FIELD
    var textPrice by remember {
        mutableStateOf("")
    }
    OutlinedTextField(value = textPrice, onValueChange = {
            newTextPrice -> textPrice = newTextPrice

    },
        label = { Text(text = "Price") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number

        )
    )


}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    ConcessionariaTheme {
        MainScreen()
    }
}