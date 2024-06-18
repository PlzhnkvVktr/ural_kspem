package ru.avem.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.sharp.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.avem.components.ActionButton
import ru.avem.components.ComboBox
import ru.avem.components.Header
import ru.avem.viewmodels.AuthScreenViewModel
import ru.avem.viewmodels.MainScreenViewModel

class SelectionTestObjectScreen: Screen {


    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { MainScreenViewModel() }
        val navigator = LocalNavigator.currentOrThrow
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { Header() },
            content = {
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth().padding(15.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Объекты испытания",
                            style = MaterialTheme.typography.h2
                        )
                    }
                    Column {
//                        TestObjectCard(viewModel, viewModel.factoryNumber1, viewModel.selectedTI1, viewModel.card1)
//                        TestObjectCard(viewModel, viewModel.factoryNumber2, viewModel.selectedTI2, viewModel.card2)
//                        TestObjectCard(viewModel, viewModel.factoryNumber3, viewModel.selectedTI3, viewModel.card3)
                    }
                    Row {
                        ActionButton(
                            text = "Вперед",
                            pic = Icons.Filled.NextPlan,
                            disabled = viewModel.card1.value && viewModel.factoryNumber1.value.isNotEmpty()
                                    || viewModel.card2.value && viewModel.factoryNumber2.value.isEmpty()
                                    || viewModel.card3.value && viewModel.factoryNumber3.value.isEmpty(),
                            onClick = {
                                viewModel.createTestItemList()
                                navigator.push(MainScreen())
                            }
                        )
                    }
                }
            },
        )
    }
}

//@Composable
//fun TestObjectCard (viewModel: MainScreenViewModel, factoryNumber: MutableState<String>, selectedTI: MutableState<String>, isVisible: MutableState<Boolean> = mutableStateOf(true)) {
//
//
//    Card(
//        modifier = Modifier.fillMaxWidth().height(200.dp).padding(15.dp),
//        border = BorderStroke(2.dp, Color.Gray)
//    ) {
//        if (isVisible.value) {
//            Row(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Column(
//                    modifier = Modifier.fillMaxWidth(0.88f).fillMaxHeight(),
//                    verticalArrangement = Arrangement.SpaceAround
//                ) {
//                    Row(
//                        modifier = Modifier.fillMaxWidth().height(70.dp),
//                        horizontalArrangement = Arrangement.SpaceAround,
//                        Alignment.CenterVertically
//                    ) {
//                        Text(
//                            modifier = Modifier.width(200.dp),
//                            text = "Заводской номер",
//                            style = MaterialTheme.typography.h5,)
//                        OutlinedTextField(
//                            value = factoryNumber.value,
//                            modifier = Modifier.fillMaxWidth(0.8f).height(56.dp),
//                            textStyle = MaterialTheme.typography.h5,
//                            placeholder = {
//                                Text(
//                                    text = "Введите серийный номер",
//                                    style = MaterialTheme.typography.subtitle1
//                                ) },
//                            onValueChange = { factoryNumber.value = it }
//                        )
//                    }
//                    Row(
//                        modifier = Modifier.fillMaxWidth().height(70.dp),
//                        horizontalArrangement = Arrangement.SpaceAround,
//                        Alignment.CenterVertically
//                    ) {
//                        Text(
//                            modifier = Modifier.width(200.dp),
//                            text = "Тип",
//                            style = MaterialTheme.typography.h5
//                        )
//                        ComboBox(selectedTI, modifier = Modifier.fillMaxWidth(0.8f), items = viewModel.typesTI)
//                    }
//                }
//                Column(
//                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Button(
//                        onClick = {
//                            isVisible.value = !isVisible.value
//                        }
//                    ) {
//                        Icon(
//                            Icons.Filled.Delete,
//                            contentDescription = "Удалить",
//                            modifier = Modifier.size(40.dp)
//                        )
//                    }
//                }
//            }
//        } else {
//            Button(
//                onClick = { isVisible.value = !isVisible.value }
//            ) {
//                Icon(
//                    Icons.Filled.Add,
//                    contentDescription = "Добавить",
//                    modifier = Modifier.size(50.dp)
//                )
//            }
//        }
//    }
//}