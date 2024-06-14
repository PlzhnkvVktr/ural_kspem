package ru.avem.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun ParamOIRow(
    text: String,
    param: MutableState<String>,
    min: Number, max: Number,
    isNewTI: Boolean,
    newParam: MutableState<String>,
    isError: MutableState<Boolean>
) {
    fun validateParam(param: MutableState<String>) =
        param.value.toDoubleOrNull() != null && param.value.toDouble() > max.toDouble()
                || param.value.toDoubleOrNull() != null && param.value.toDouble() < min.toDouble()
                || param.value == ""
                || param.value.toList().last().toString() == "."

    isError.value = if (!isNewTI) validateParam(param) else validateParam(newParam)

    Row(
        modifier = Modifier.fillMaxWidth().height(60.dp).padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Text(text = text, style = MaterialTheme.typography.subtitle1)
        }
        Column(
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            OutlinedTextField(
                value = if (!isNewTI) param.value else newParam.value,
                modifier = Modifier.fillMaxWidth().height(50.dp)
                    .border(2.dp, if (isError.value) Color.Red else Color.Green),
                textStyle = MaterialTheme.typography.body1,
                placeholder = {
                    Text(
                        text = "min: $min, max: $max",
                        style = MaterialTheme.typography.caption,
                    )
                },
                isError = if (!isNewTI) validateParam(param) else validateParam(newParam),
                onValueChange = {
                    if (!isNewTI) {
                        if (it.isEmpty()) param.value = it
                        it.toDoubleOrNull()?.let { value ->
                            isError.value = value in (min.toDouble()..max.toDouble())
                            param.value = it
                        }
                    } else {
                        if (it.isEmpty()) newParam.value = it
                        it.toDoubleOrNull()?.let { value ->
                            isError.value = value in (min.toDouble()..max.toDouble())
                            newParam.value = it
                        }
                    }
                },
            )
        }
    }
}

// рабочая регулярка, доступен ввод только Double и Int
fun isValidText(text: String): Boolean {
    return text.matches(Regex(pattern = "^-?\\d*[.]?\\d*\$"))
}


