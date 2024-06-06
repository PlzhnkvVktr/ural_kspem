package ru.avem.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.avem.components.ActionButton
import ru.avem.components.ComboBox
import ru.avem.components.Header
import ru.avem.components.TestListContainer
import ru.avem.db.DBManager
import ru.avem.modules.tests.CustomController
import ru.avem.viewmodels.MainScreenViewModel
import kotlin.concurrent.thread
import ru.avem.modules.tests.ikas.IKASScreen
import ru.avem.modules.tests.mgr.MGRScreen

class MainScreen() : Screen {
    @Composable
    override fun Content() {

        val viewModel = rememberScreenModel { MainScreenViewModel() }
        val navigator = LocalNavigator.currentOrThrow
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
//        viewModel.typesTI = DBManager.getAllTI().ifEmpty { listOf("") }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { Header() },
            content = {
                Column (
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Заполните все поля",  style = MaterialTheme.typography.h3)
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth().padding(top = 10.dp).height(100.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.width(200.dp),
                            text = "Заводской номер",
                            style = MaterialTheme.typography.h5,
                        )

                        OutlinedTextField(
                            value = viewModel.factoryNumber.value,
                            modifier = Modifier.fillMaxWidth(0.8f),
                            textStyle = MaterialTheme.typography.h5,
                            placeholder =  {
                                Text(
                                    text = "Enter the serial number of the test object",
                                    style = MaterialTheme.typography.subtitle1,
                                    modifier = Modifier.padding(top = 6.dp)
                                ) },
                            onValueChange = {viewModel.factoryNumber.value = it}
                        )
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth().padding(top = 10.dp).height(100.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.width(200.dp),
                            text = "Тип",
                            style = MaterialTheme.typography.h5,)
                        ComboBox(viewModel.selectedTI, modifier = Modifier.fillMaxWidth(0.8f), items = viewModel.typesTI)
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth().padding(top = 10.dp).fillMaxHeight(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Column (
                            modifier = Modifier.width(200.dp).fillMaxHeight(.8f), verticalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(
                                modifier = Modifier.width(200.dp),
                                text = "Испытания",
                                style = MaterialTheme.typography.h5,)
                            ActionButton(
                                text = if (!viewModel.allCheckedButton.value) "Выбрать все" else "Отменить все",
                                pic = if (!viewModel.allCheckedButton.value) Icons.Filled.Check else Icons.Filled.Close) {
                                thread {
                                    if (!viewModel.allCheckedButton.value) {
                                        viewModel.allCheckedButton.value = true
                                        viewModel.testList.clear()
                                        viewModel.testMap.forEach {
                                                item -> item.value.value = true
                                            when (item.key) {
                                                MainScreenViewModel.TestEnum.nameMGR -> viewModel.testList.add(MGRScreen(viewModel))
                                                MainScreenViewModel.TestEnum.nameVIU -> viewModel.testList.add(IKASScreen(viewModel))
                                                MainScreenViewModel.TestEnum.nameIKAS -> viewModel.testList.add(MGRScreen(viewModel))
                                                MainScreenViewModel.TestEnum.nameHH -> viewModel.testList.add(IKASScreen(viewModel))
                                                MainScreenViewModel.TestEnum.nameMV -> viewModel.testList.add(MGRScreen(viewModel))
                                            }
                                        }
                                    } else {
                                        viewModel.allCheckedButton.value = false
                                        viewModel.testMap.forEach { item -> item.value.value = false }
                                        viewModel.testList.clear()
                                    }
                                    viewModel.testsLine.value = viewModel.testList.iterator()
                                    viewModel.startTestButton.value = viewModel.testsLine.value.hasNext()
                                }
                            }
                            ActionButton("Старт", Icons.Filled.PlayArrow, (viewModel.startTestButton.value && viewModel.factoryNumber.value != "")) {
                                if (viewModel.testsLine.value.hasNext()) {
                                    thread {
                                        CustomController.testObjectName.value = viewModel.selectedTI.value
                                        CustomController.testObject = DBManager.getTI(CustomController.testObjectName.value)
                                        navigator.push(viewModel.testsLine.value.next())
                                        viewModel.testMap.forEach { item -> item.value.value = false }
                                        viewModel.startTestButton.value = false
                                        viewModel.allCheckedButton.value = false
                                    }
                                }
                            }
                        }
                        TestListContainer(viewModel)
                    }
                }
            }
        )
    }
}