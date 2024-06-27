package ru.avem.modules.tests.hh

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
                Text(text = viewModel.nameIKAS, style = MaterialTheme.typography.h5)
            }
            Row(Modifier.background(Color.Gray)) {
                TableCell(text = "U uv, B")
                TableCell(text = "U vw, B")
                TableCell(text = "U wu, B")
                TableCell(text = "I u, A")
                TableCell(text = "I v, A")
                TableCell(text = "I w, A")
            }
            Row() {
                TableCell(text = viewModel.u_uv.value)
                TableCell(text = viewModel.u_vw.value)
                TableCell(text = viewModel.u_wu.value)
                TableCell(text = viewModel.i_u.value)
                TableCell(text = viewModel.i_v.value)
                TableCell(text = viewModel.i_w.value)
            }

            Row(Modifier.background(Color.Gray)) {
                TableCell(text = "Наименование")
                TableCell(text = "U uv, B")
                TableCell(text = "U vw, B")
                TableCell(text = "U wu, B")
                TableCell(text = "I u, A")
                TableCell(text = "I v, A")
                TableCell(text = "I w, A")
            }
            repeat(3) {
                Row() {
                    TableCell(text = viewModel.listTestItems[it].name.value)
                    TableCell(text = viewModel.listTestItems[it].u_uv_hh.value)
                    TableCell(text = viewModel.listTestItems[it].u_vw_hh.value)
                    TableCell(text = viewModel.listTestItems[it].u_wu_hh.value)
                    TableCell(text = viewModel.listTestItems[it].i_u_hh.value)
                    TableCell(text = viewModel.listTestItems[it].i_v_hh.value)
                    TableCell(text = viewModel.listTestItems[it].i_w_hh.value)
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