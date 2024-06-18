package ru.avem.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import ru.avem.components.*
import ru.avem.db.DBManager
import ru.avem.db.DBManager.addTI
import ru.avem.db.DBManager.deleteTestItemById
import ru.avem.db.DBManager.getAllTI
import ru.avem.viewmodels.EditorOIScreenViewModel
import ru.avem.viewmodels.MainScreenViewModel

class EditorOIScreen() : Screen {

    @Composable
    override fun Content() {

        val viewModel = rememberScreenModel { EditorOIScreenViewModel() }
        val mainViewModel = rememberScreenModel { MainScreenViewModel() }
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

        viewModel.addNewTI.value = false

        val ti = DBManager.getTI(viewModel.selectedTI.value)
        viewModel.name.value = ti.name
        viewModel.scheme.value = ti.scheme
        viewModel.power.value = ti.power
        viewModel.u_linear.value = ti.u_linear
        viewModel.i.value = ti.i
        viewModel.i_viu.value = ti.i_viu
        viewModel.i_mz.value = ti.i_mz
        viewModel.u_viu.value = ti.u_viu
        viewModel.u_mgr.value = ti.u_mgr
        viewModel.t_viu.value = ti.t_viu
        viewModel.t_hh.value = ti.t_hh
        viewModel.t_mv.value = ti.t_mv
        viewModel.r_max.value = ti.r_max
        viewModel.r_min.value = ti.r_min
        viewModel.r20_max.value = ti.r20_max
        viewModel.r20_min.value = ti.r20_min
        viewModel.t.value = ti.t


        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { Header() },
            content = {
                Row(
                    modifier = Modifier.fillMaxSize().padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.2f).fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Объект испытания")
                        ComboBox(
                            viewModel.selectedTI,
                            modifier = Modifier.fillMaxWidth(),
                            items = viewModel.typesTI
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Button(onClick = {
                                viewModel.addNewTI.value = !viewModel.addNewTI.value
                            }) {
                                Icon(
                                    if (!viewModel.addNewTI.value) Icons.Filled.Add else Icons.Filled.ArrowBack,
                                    contentDescription = "Информация о приложении", modifier = Modifier.size(50.dp)
                                )
                            }
                            Button(onClick = {
                                viewModel.deleteTI()
                            }, enabled = getAllTI().size != 1) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "Информация о приложении", modifier = Modifier.size(50.dp)
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(0.6f).fillMaxHeight().border(2.dp, Color.DarkGray)
                    ){
                        CreatorOI(viewModel)
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(0.3f).fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        ActionButton(
                            if (!viewModel.addNewTI.value) "Сохранить" else "Добавить",
                            Icons.Filled.Check,
                            disabled = !(viewModel.validateField1.value || viewModel.validateField2.value || viewModel.validateField3.value
                                    || viewModel.validateField4.value || viewModel.validateField5.value || viewModel.validateField6.value
                                    || viewModel.validateField7.value || viewModel.validateField8.value || viewModel.validateField9.value
                                    || viewModel.validateField10.value || viewModel.validateField11.value || viewModel.validateField12.value
                                    || viewModel.validateField13.value || viewModel.validateField14.value || viewModel.validateField15.value
                                    )
                        ) {
                            viewModel.addNewTI()
                            viewModel.openDialog()
                            viewModel.typesTI = getAllTI().ifEmpty { listOf("") }
                            }
                        }
                    }
                    if (viewModel.dialogWindow.value) {
                        TestDialog("ОК")
                    }
                },
                bottomBar = {  }
            )
    }
}