package ru.avem.modules.tests.ikas

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.sharp.Stop
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.avem.common.ProtocolBuilder
import ru.avem.components.*
import ru.avem.components.LogsList
import ru.avem.modules.tests.Test
import ru.avem.screens.MainScreen
import ru.avem.viewmodels.MainScreenViewModel
import ru.avem.db.DBManager.addNewProtocol
import ru.avem.modules.tests.CustomController.isStartButton
import ru.avem.modules.tests.CustomController.isTestRunning
import ru.avem.modules.tests.CustomController.testObject
import java.lang.Thread.sleep
import kotlin.concurrent.thread


class IKASScreen(private var mainViewModel: MainScreenViewModel) : Test() {
    override val testName: String
        get() = TestEnum.nameIKAS.testName

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { IKASViewModel() }
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val navigator = LocalNavigator.currentOrThrow

        LifecycleEffect(onStarted = { viewModel.clearFields() })

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { },
            content = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.border(2.dp, Color.Black)
                    ) {
//                        SpecifiedParamsList(viewModel.currentTest)
                        Column(
                            modifier = Modifier.fillMaxHeight(0.6f).fillMaxWidth(0.8f).border(1.dp, Color.LightGray)
                        ) {
                            IKASTestWindow(viewModel)
                            if (viewModel.warningUV.value) {
                                TestDialog(
                                    text = "Подключите провода ИКАС-А и ИКАС-N к UV",
                                    other = true,
                                    action1 = {viewModel.warningUV.value = false},
                                    action2 = {
                                        isTestRunning.value = false
                                        viewModel.warningUV.value = false
                                    }
                                )
                            }
                            if (viewModel.warningVW.value) {
                                TestDialog(
                                    text = "Подключите провода ИКАС-А и ИКАС-N к VW",
                                    other = true,
                                    action1 = {viewModel.warningVW.value = false},
                                    action2 = {
                                        isTestRunning.value = false
                                        viewModel.warningVW.value = false
                                    }
                                )
                            }
                            if (viewModel.warningWU.value) {
                                TestDialog(
                                    text = "Подключите провода ИКАС-А и ИКАС-N к WU",
                                    other = true,
                                    action1 = {viewModel.warningWU.value = false},
                                    action2 = {
                                        isTestRunning.value = false
                                        viewModel.warningWU.value = false
                                    }
                                )
                            }
                        }
                        ProtectionStatusContainer()
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        ActionButton("cancel all", Icons.Filled.Close) {
                            isTestRunning.value = false
                            addNewProtocol(testObject.name, testObject, mainViewModel.factoryNumber1.value)
                            mainViewModel.testList.clear()
                            navigator.pop()
                        }
                        ActionButton(
                            if (!isTestRunning.value) "start" else "stop",
                            if (!isTestRunning.value) Icons.Filled.PlayArrow else Icons.Sharp.Stop,
                            viewModel.waiting.value
                        ) {
                            if (!isTestRunning.value) {
                                start(viewModel, mainViewModel.testItemLine)
                                thread {
                                    viewModel.waiting.value = false
                                    sleep(1000)
                                    viewModel.waiting.value = true
                                }
                            } else {
                                isTestRunning.value = false
                                thread {
                                    viewModel.waiting.value = false
                                    sleep(1000)
                                    viewModel.waiting.value = true
                                }
                            }
                        }
                        ActionButton("next", Icons.Filled.ArrowForward, !isTestRunning.value) {
                            if (mainViewModel.testsLine.value.hasNext()) {
                                navigator.replace(mainViewModel.testsLine.value.next())
                            } else {
                                mainViewModel.testList.clear()
                                navigator.replace(MainScreen())
                                addNewProtocol(testObject.name, testObject, mainViewModel.factoryNumber1.value)
                                ProtocolBuilder.clear()
                            }
                        }
                    }
                    LogsList()
                }
                if (isStartButton.value) {
                    TestDialog()
                }
            },
            bottomBar = { }
        )
    }

}