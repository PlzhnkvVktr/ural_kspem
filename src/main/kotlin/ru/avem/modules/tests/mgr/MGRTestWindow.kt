package ru.avem.modules.tests.mgr

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
fun MGRTestWindow (viewModel: TestScreenViewModel) {

    Column(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column (
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Row (modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp), horizontalArrangement = Arrangement.Center) {
                Text(text = viewModel.nameMGR, style = MaterialTheme.typography.h5)
            }
            Row (modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp), horizontalArrangement = Arrangement.Center) {
                Text(text = "Измеренные значения", style = MaterialTheme.typography.h6)
            }
            Row(Modifier.background(Color.Gray)) {
                TableCell(text = "Наименование")
                TableCell(text = "U номинальное")
                TableCell(text = "U")
                TableCell(text = "R15, Ом")
                TableCell(text = "R60, Ом")
                TableCell(text = "Abs")
            }
            repeat(3) {
                Row() {
                    TableCell(text = viewModel.listTestItems[it].name.value)
                    TableCell(text = viewModel.listTestItems[it].specifiedMgrU.value)
                    TableCell(text = viewModel.listTestItems[it].mgrU.value)
                    TableCell(text = viewModel.listTestItems[it].r15.value)
                    TableCell(text = viewModel.listTestItems[it].r60.value)
                    TableCell(text = viewModel.listTestItems[it].kABS.value)
                }
            }

        }
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Row(Modifier.background(Color.Gray).fillMaxWidth(0.25f)) {
                TableCell(text = "Результат")
            }

            Row(Modifier.fillMaxWidth(0.25f)) {
                TableCell(text = viewModel.result.value)
            }
        }
    }
}