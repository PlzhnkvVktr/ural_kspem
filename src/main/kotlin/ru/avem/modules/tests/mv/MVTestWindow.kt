package ru.avem.modules.tests.mv

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
fun MVTestWindow (viewModel: TestScreenViewModel) {

    Column(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column (
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Row (modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp), horizontalArrangement = Arrangement.Center) {
                Text(text = viewModel.nameMV, style = MaterialTheme.typography.h5)
            }
//            Row (modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp), horizontalArrangement = Arrangement.Center) {
//                Text(text = "Номинальные параметры", style = MaterialTheme.typography.h6)
//            }
//            Row(Modifier.background(Color.Gray)) {
//                TableCell(text = "Um")
//            }
//            Row(Modifier.padding(bottom = 20.dp)) {
//                TableCell(text = viewModel.specifiedMgrU.value)
//            }
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
//            Row() {
//                TableCell(text = viewModel.name_1.value)
//                TableCell(text = viewModel.specifiedMgrU_1.value)
//                TableCell(text = viewModel.mgrU_1.value)
//                TableCell(text = viewModel.R15_1.value)
//                TableCell(text = viewModel.R60_1.value)
//                TableCell(text = viewModel.kABS_1.value)
//            }
//            Row() {
//                TableCell(text = viewModel.name_2.value)
//                TableCell(text = viewModel.specifiedMgrU_2.value)
//                TableCell(text = viewModel.mgrU_2.value)
//                TableCell(text = viewModel.R15_2.value)
//                TableCell(text = viewModel.R60_2.value)
//                TableCell(text = viewModel.kABS_2.value)
//            }
//            Row() {
//                TableCell(text = viewModel.name_3.value)
//                TableCell(text = viewModel.specifiedMgrU_3.value)
//                TableCell(text = viewModel.mgrU_3.value)
//                TableCell(text = viewModel.R15_3.value)
//                TableCell(text = viewModel.R60_3.value)
//                TableCell(text = viewModel.kABS_3.value)
//            }
//            Column (
//                modifier = Modifier.padding(top = 20.dp)
//            ) {
//                Row(Modifier.background(Color.Gray).fillMaxWidth(0.5f)) {
//                    TableCell(text = "t")
//                }
//
//                Row(Modifier.fillMaxWidth(0.5f)) {
//                    TableCell(text = viewModel.tempAmb.value)
//                }
//            }
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