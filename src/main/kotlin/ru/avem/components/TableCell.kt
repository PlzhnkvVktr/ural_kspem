package ru.avem.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float = .2f,
    padding: Float = 8f
){
    Text(
        text = text,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(padding.dp),
        textAlign = TextAlign.Center,
    )
}