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
import ru.avem.components.*
import ru.avem.db.DBManager
import ru.avem.modules.tests.CustomController
import ru.avem.modules.tests.hh.HHScreen
import ru.avem.viewmodels.MainScreenViewModel
import kotlin.concurrent.thread
import ru.avem.modules.tests.ikas.IKASScreen
import ru.avem.modules.tests.mgr.MGRScreen
import ru.avem.modules.tests.mv.MVScreen
import ru.avem.modules.tests.viu.VIUScreen

class MainScreen() : Screen {
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
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.35f),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TestObjectCard(viewModel, viewModel.factoryNumber1, viewModel.selectedTI1, viewModel.card1)
                        TestObjectCard(viewModel, viewModel.factoryNumber2, viewModel.selectedTI2, viewModel.card2)
                        TestObjectCard(viewModel, viewModel.factoryNumber3, viewModel.selectedTI3, viewModel.card3)
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth().padding(top = 10.dp).fillMaxHeight(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column (
                            modifier = Modifier
                                .width(200.dp)
                                .fillMaxHeight(.6f),
                            verticalArrangement = Arrangement.SpaceBetween
                        ){
                            ActionButton(
                                text = if (!viewModel.allCheckedButton.value) "Выбрать все" else "Отменить все",
                                pic = if (!viewModel.allCheckedButton.value) Icons.Filled.Check else Icons.Filled.Close,
                                onClick = viewModel::selectAll
                            )
                            ActionButton(
                                text = "Старт",
                                pic = Icons.Filled.PlayArrow,
                                disabled = (
                                        viewModel.startTestButton.value
                                                && (viewModel.factoryNumber1.value.isNotEmpty() || viewModel.card1.value)
//                                                || (viewModel.factoryNumber2.value.isNotEmpty() && viewModel.card2.value)
//                                                || (viewModel.factoryNumber3.value.isNotEmpty() && viewModel.card3.value)
                                        ),
                                onClick = { if (viewModel.testsLine.value.hasNext()) viewModel.startTests(navigator) }
                            )
                        }
                        TestListContainer(viewModel)
                    }
                }
            }
        )
    }
}