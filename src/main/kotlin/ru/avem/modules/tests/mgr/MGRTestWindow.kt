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

@Composable
fun MGRTestWindow (viewModel: MGRViewModel) {

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
                Text(text = "set values", style = MaterialTheme.typography.h6)
            }
            Row(Modifier.background(Color.Gray)) {
                TableCell(text = "Um")
            }
            Row(Modifier.padding(bottom = 20.dp)) {
                TableCell(text = viewModel.specifiedMgrU.value)
            }
            Row (modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp), horizontalArrangement = Arrangement.Center) {
                Text(text = "measured values", style = MaterialTheme.typography.h6)
            }
            Row(Modifier.background(Color.Gray)) {
                TableCell(text = "Um")
                TableCell(text = "R15, M" + "ohm")
                TableCell(text = "R60, M" + "ohm")
                TableCell(text = "Abs")
            }
            Row() {
                TableCell(text = viewModel.U.value)
                TableCell(text = viewModel.R15.value)
                TableCell(text = viewModel.R60.value)
                TableCell(text = viewModel.kABS.value)
            }
            Column (
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Row(Modifier.background(Color.Gray).fillMaxWidth(0.5f)) {
                    TableCell(text = "t")
                }

                Row(Modifier.fillMaxWidth(0.5f)) {
                    TableCell(text = viewModel.tempAmb.value)
                }
            }
        }
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Row(Modifier.background(Color.Gray).fillMaxWidth(0.25f)) {
                TableCell(text = "res")
            }

            Row(Modifier.fillMaxWidth(0.25f)) {
                TableCell(text = viewModel.result.value)
            }
        }
    }
}