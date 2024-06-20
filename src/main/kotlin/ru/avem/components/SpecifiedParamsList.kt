package ru.avem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.avem.db.TestItem

@Composable
fun SpecifiedParamsList(testObjectInfo: MutableState<TestItem?>) {

    Column (
        modifier = Modifier.fillMaxHeight(0.6f).fillMaxWidth(0.12f).border(1.dp, Color.LightGray).padding(10.dp).verticalScroll(
            rememberScrollState()
        ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Номинальные параметры", style = MaterialTheme.typography.h5, textAlign = TextAlign.Center)

        testObjectInfo.value?.let { SpecifiedParamsItem("Наименование", it.name) }
        testObjectInfo.value?.let { SpecifiedParamsItem("Схема", it.scheme.toString()) }
        testObjectInfo.value?.let { SpecifiedParamsItem("Ток", it.i) }
        testObjectInfo.value?.let { SpecifiedParamsItem("Напряжение", it.u_linear) }
        testObjectInfo.value?.let { SpecifiedParamsItem("Мощность", it.power) }

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