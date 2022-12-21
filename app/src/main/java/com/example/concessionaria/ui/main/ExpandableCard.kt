@file:OptIn(ExperimentalFoundationApi::class)

package com.example.concessionaria.ui.main

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.concessionaria.ui.theme.Shapes

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun ExpandableCard(
    title: String,
    titleFontSize: TextUnit = MaterialTheme.typography.h6.fontSize,
    titleFontWeight: FontWeight = FontWeight.Bold,
    description: String,
    descriptionFontSize: TextUnit = MaterialTheme.typography.subtitle1.fontSize,
    descriptionFontWeight: FontWeight = FontWeight.Normal,
    descriptionMaxLines: Int = 4,
    shape: Shape = Shapes.medium,
    padding: Dp = 12.dp,
    status: Boolean
) {

    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )
    var ativo by remember {
        mutableStateOf(status) }


    val sb = StringBuilder()
    val textoFinal: String
    val cor: Color
    val linha: TextDecoration
    val sold: String

    if (status && ativo){
        textoFinal = sb.append(description).append("This Vehicle is avaiable").toString()
        cor = Color.Green
        linha = TextDecoration.None
        sold = ""
    }else{
        textoFinal = sb.append(description).append("This Vehicle is sold").toString()
        cor = Color.Red
        linha = TextDecoration.LineThrough
        sold = "Sold"
    }



    Card(
        modifier = Modifier
            .border(0.5.dp, Color.Gray)
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ).combinedClickable(
                onClick = { expandedState = !expandedState },
                onLongClick = { ativo = !ativo }

            ),
            shape = shape


    ) {
        Box(
            modifier = Modifier
                .background(cor)
                .padding(9.dp)
                .height(1.dp)

        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = title,
                    fontSize = titleFontSize,
                    fontWeight = titleFontWeight,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textDecoration = linha
                )
                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = sold,
                    fontSize = descriptionFontSize,
                    fontWeight = descriptionFontWeight,
                    maxLines = descriptionMaxLines,
                    overflow = TextOverflow.Ellipsis,
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState)
                        .combinedClickable(
                            onClick = {},
                            onLongClick = { ativo = !ativo }),
                        onClick = {expandedState = !expandedState }
                ){
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )}
            }
            if (expandedState) {
                Text(
                    text = textoFinal,
                    fontSize = descriptionFontSize,
                    fontWeight = descriptionFontWeight,
                    maxLines = descriptionMaxLines,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


// Exemplo de como puxar
@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun ExpandableCardPreview() {
    ExpandableCard(
        title = "Carro",
        description = "Type: Hatch \n Price: 12522.00 \n Status: ",
        status = true
    )
}