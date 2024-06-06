package ru.avem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SpecifiedParamsList () {


    Column (
        modifier = Modifier.fillMaxHeight(0.6f).fillMaxWidth(0.2f).border(1.dp, Color.LightGray).padding(10.dp).verticalScroll(
            rememberScrollState()
        ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "set values", style = MaterialTheme.typography.h5)


//        SpecifiedParamsItem(word("Voltage high-voltage test setup, V"), testObject.specifiedViuU)
//        SpecifiedParamsItem(word("Test Time high-voltage test setup, V"), testObject.specifiedViuTime)

    }
}

@Composable
fun SpecifiedParamsItem (text: String, paramValue: String) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.5f)
            .border(1.dp, Color.LightGray)
            .padding(10.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.fillMaxWidth().background(Color.LightGray),
            textAlign = TextAlign.Center
        )
        Text(
//            text = paramValue.value.toString(),
            text = when (paramValue) {
                "true" -> "∆"
                "false" -> "λ"
                else -> paramValue
            },
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.fillMaxWidth().padding(top = 7.dp),
            textAlign = TextAlign.Center
        )
    }
}