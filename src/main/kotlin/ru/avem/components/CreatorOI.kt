package ru.avem.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.avem.viewmodels.EditorOIScreenViewModel

@Composable
fun CreatorOI (viewModel: EditorOIScreenViewModel) {

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ){
        Text(text = "Значения", style = MaterialTheme.typography.h4, modifier = Modifier.padding(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(0.9f).height(120.dp).padding(start = 37.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Схема", style = MaterialTheme.typography.subtitle1)
            Column (
                modifier = Modifier
            ) {
                if (viewModel.addNewTI.value) {
                    Row{
                        RadioButton(
                            selected = viewModel.newScheme.value,
                            onClick = { viewModel.newScheme.value = true },
                        )
                        Text(text = "∆", style = MaterialTheme.typography.h4, modifier = Modifier.padding(top = 13.dp))
                    }
                    Row{
                        RadioButton(
                            selected = !viewModel.newScheme.value,
                            onClick = { viewModel.newScheme.value = false },
                        )
                        Text(text = "λ", style = MaterialTheme.typography.h4, modifier = Modifier.padding(top = 7.dp))
                    }
                } else {
                    Row{
                        RadioButton(
                            selected = viewModel.scheme.value,
                            onClick = { viewModel.scheme.value = true }
                        )
                        Text(text = "∆", style = MaterialTheme.typography.h4, modifier = Modifier.padding(top = 13.dp))
                    }
                    Row{
                        RadioButton(
                            selected = !viewModel.scheme.value,
                            onClick = { viewModel.scheme.value = false },
                        )
                        Text(text = "λ", style = MaterialTheme.typography.h4, modifier = Modifier.padding(top = 7.dp))
                    }
                }

            }
        }
        if (viewModel.addNewTI.value) {
            Row(
                modifier = Modifier.fillMaxWidth().height(60.dp).padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    Text(text = "Тип", style = MaterialTheme.typography.subtitle1)
                }
                Column(
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    OutlinedTextField(
                        value = viewModel.newName.value,
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        textStyle = MaterialTheme.typography.body1,
                        onValueChange = {
                            viewModel.newName.value = it
                        },
                    )
                }
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth().height(60.dp).padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    Text(text = "Тип", style = MaterialTheme.typography.subtitle1)
                }
                Column(
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    OutlinedTextField(
                        value = viewModel.name.value,
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        textStyle = MaterialTheme.typography.body1,
                        onValueChange = {
                            viewModel.name.value = it
                        },
                    )
                }
            }
        }

        ParamOIRow("Мощность, кВт", viewModel.power, 1, 100, viewModel.addNewTI.value, viewModel.newPower, viewModel.validateField1)
        ParamOIRow("Напряжение линейноеб В", viewModel.u_linear, 380, 400, viewModel.addNewTI.value, viewModel.newU_linear, viewModel.validateField2)
        ParamOIRow("Токб А", viewModel.i, 1, 100, viewModel.addNewTI.value, viewModel.newI, viewModel.validateField3)
        ParamOIRow("Напряжение ВИУ, В", viewModel.u_viu, 0, 2000, viewModel.addNewTI.value, viewModel.newU_viu, viewModel.validateField4)
        ParamOIRow("Напряжение испытания мегаомметром, В", viewModel.u_mgr, 500, 2500, viewModel.addNewTI.value, viewModel.newU_mgr, viewModel.validateField5)
        ParamOIRow("Температурный Kr для расчета, приведённого R, 1/°C", viewModel.t, -99999999, 999999999, viewModel.addNewTI.value, viewModel.newT, viewModel.validateField6)
        ParamOIRow("Сопротивление изоляции максимальное, МОм", viewModel.r_max, 50, 200000, viewModel.addNewTI.value, viewModel.newR_max, viewModel.validateField7)
        ParamOIRow("Сопротивление изоляции минимальное, МОм", viewModel.r_min, 50, 200000, viewModel.addNewTI.value, viewModel.newR_min, viewModel.validateField8)
        ParamOIRow("Сопротивление фазы статора при 20°С максимальное, Ом", viewModel.r20_max, 0, 999999999, viewModel.addNewTI.value, viewModel.newR20_max, viewModel.validateField9)
        ParamOIRow("Сопротивление фазы статора при 20°С минимальное, Ом", viewModel.r20_min, 0, 99999999, viewModel.addNewTI.value, viewModel.newR20_min, viewModel.validateField10)
        ParamOIRow("Время испытания ВИУ, сек", viewModel.t_viu, 0, 3600, viewModel.addNewTI.value, viewModel.newT_viu, viewModel.validateField11)
        ParamOIRow("Время испытания ХХ, мин", viewModel.t_hh, 0, 999999999, viewModel.addNewTI.value, viewModel.newT_hh, viewModel.validateField12)
        ParamOIRow("Время испытания МВЗ, сек", viewModel.t_mv, 0, 60, viewModel.addNewTI.value, viewModel.newT_mv, viewModel.validateField13)
        ParamOIRow("Допустимый ток утечки ВИУ, А", viewModel.i_viu, 0, 0.24, viewModel.addNewTI.value, viewModel.newI_viu, viewModel.validateField14)
        ParamOIRow("Допустимое повышение тока при МВЗ, %", viewModel.i_mz, 0, 5, viewModel.addNewTI.value, viewModel.newI_mz, viewModel.validateField15)
    }
}