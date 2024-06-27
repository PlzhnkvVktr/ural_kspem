package ru.avem.modules.tests.viu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.avem.components.TableCell
import ru.avem.viewmodels.TestScreenViewModel

@Composable
fun VIUTestWindow (viewModel: TestScreenViewModel) {

    Column(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column (
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Row (modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp), horizontalArrangement = Arrangement.Center) {
                Text(text = viewModel.nameVIU, style = MaterialTheme.typography.h5)
            }

            Row (modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp), horizontalArrangement = Arrangement.Center) {
                Text(text = "Измеренные значения", style = MaterialTheme.typography.h6)
            }
            Row(Modifier.background(Color.Gray)) {
                TableCell(text = "Наименование")
                TableCell(text = "U, В")
                TableCell(text = "I, мА")
                TableCell(text = "t, сек")
                TableCell(text = "Результат")
            }
            repeat(3) {
                Row() {
                    TableCell(text = viewModel.listTestItems[it].name.value)
                    TableCell(text = viewModel.listTestItems[it].u_viu.value)
                    TableCell(text = viewModel.listTestItems[it].i_viu.value)
                    TableCell(text = viewModel.listTestItems[it].t_viu.value)
                    TableCell(text = viewModel.listTestItems[it].res_viu.value)
                }
            }
        }
    }
}