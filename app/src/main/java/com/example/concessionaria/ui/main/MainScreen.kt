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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.concessionaria.ui.main.ExpandableCardPreview
import com.example.concessionaria.ui.main.ExposedDropMenuStateHolderTipoCarro
import com.example.concessionaria.ui.main.MainFields
import com.example.concessionaria.ui.main.rememberExposedDropMenuStateHolderTipoCarro
import com.example.concessionaria.ui.theme.ConcessionariaTheme

@Composable
fun MainScreen(){
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        MainFields()
        ExpandableCardPreview()
    }

}



@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    ConcessionariaTheme {
        MainScreen()
    }
}