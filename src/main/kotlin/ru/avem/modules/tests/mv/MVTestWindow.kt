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
                TableCell(text = "до U uv, B")
                TableCell(text = "до U vw, B")
                TableCell(text = "до U wu, B")
                TableCell(text = "до I u, A")
                TableCell(text = "до I v, A")
                TableCell(text = "до I w, A")
            }
            repeat(3) {
                Row() {
                    TableCell(text = viewModel.listTestItems[it].name.value)
                    TableCell(text = viewModel.listTestItems[it].before_u_uv_mv.value)
                    TableCell(text = viewModel.listTestItems[it].before_u_vw_mv.value)
                    TableCell(text = viewModel.listTestItems[it].before_u_wu_mv.value)
                    TableCell(text = viewModel.listTestItems[it].before_i_u_mv.value)
                    TableCell(text = viewModel.listTestItems[it].before_i_v_mv.value)
                    TableCell(text = viewModel.listTestItems[it].before_i_w_mv.value)
                }
            }
            Row(Modifier.background(Color.Gray)) {
                TableCell(text = "Наименование")
                TableCell(text = "после U uv, B")
                TableCell(text = "после U vw, B")
                TableCell(text = "после U wu, B")
                TableCell(text = "после I u, A")
                TableCell(text = "после I v, A")
                TableCell(text = "после I w, A")
            }
            repeat(3) {
                Row() {
                    TableCell(text = viewModel.listTestItems[it].name.value)
                    TableCell(text = viewModel.listTestItems[it].after_u_uv_mv.value)
                    TableCell(text = viewModel.listTestItems[it].after_u_vw_mv.value)
                    TableCell(text = viewModel.listTestItems[it].after_u_wu_mv.value)
                    TableCell(text = viewModel.listTestItems[it].after_i_u_mv.value)
                    TableCell(text = viewModel.listTestItems[it].after_i_v_mv.value)
                    TableCell(text = viewModel.listTestItems[it].after_i_w_mv.value)
                }
            }

        }
    }
}