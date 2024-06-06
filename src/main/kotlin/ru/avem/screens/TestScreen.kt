package ru.avem.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.sharp.Logout
import androidx.compose.material.icons.sharp.Stop
import androidx.compose.runtime.Composable
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
import ru.avem.common.ProtocolBuilder
import ru.avem.components.*
import ru.avem.db.DBManager
import ru.avem.modules.tests.CustomController
import ru.avem.modules.tests.mgr.MGRTestWindow
import ru.avem.modules.tests.mgr.start
import ru.avem.viewmodels.AuthScreenViewModel
import ru.avem.viewmodels.MainScreenViewModel
import ru.avem.viewmodels.TestScreenViewModel
import kotlin.concurrent.thread

class TestScreen: Screen {

    @Composable
    override fun Content() {
        val mainViewModel = rememberScreenModel { MainScreenViewModel() }
        val viewModel = rememberScreenModel { TestScreenViewModel() }
        val navigator = LocalNavigator.currentOrThrow
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

        Scaffold(
            scaffoldState = scaffoldState,
            content = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.border(2.dp, Color.Black)
                    ) {
                        SpecifiedParamsList()
                        Column(
                            modifier = Modifier.fillMaxHeight(0.6f).fillMaxWidth(0.8f).border(1.dp, Color.LightGray)
                        ) {

                        }
                        ProtectionStatusContainer()
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        ActionButton("cancel all", Icons.Filled.Close) {
                            CustomController.isTestRunning.value = false
                            DBManager.addNewProtocol(
                                CustomController.testObject.name,
                                CustomController.testObject,
                                mainViewModel.factoryNumber.value
                            )
                            mainViewModel.testList.clear()
                            navigator.pop()
                        }
                        ActionButton(
                            if (!CustomController.isTestRunning.value) "start" else "stop",
                            if (!CustomController.isTestRunning.value) Icons.Filled.PlayArrow else Icons.Sharp.Stop,
                            viewModel.waiting.value
                        ) {
                            if (!CustomController.isTestRunning.value) {
                                start(viewModel, CustomController.testObject)
                                thread {
                                    viewModel.waiting.value = false
                                    Thread.sleep(1000)
                                    viewModel.waiting.value = true
                                }
                            } else {
                                CustomController.isTestRunning.value = false
                                thread {
                                    viewModel.waiting.value = false
                                    Thread.sleep(1000)
                                    viewModel.waiting.value = true
                                }
                            }
                        }
                        ActionButton("next", Icons.Filled.ArrowForward, !CustomController.isTestRunning.value) {
                            if (mainViewModel.testsLine.value.hasNext()) {
//                                    addReport(viewModel, mainViewModel.factoryNumber.value)
                                navigator.replace(mainViewModel.testsLine.value.next())
                            } else {
//                                    addReport(viewModel, mainViewModel.factoryNumber.value)
                                mainViewModel.testList.clear()
                                navigator.replace(MainScreen())

                                DBManager.addNewProtocol(
                                    CustomController.testObject.name,
                                    CustomController.testObject,
                                    mainViewModel.factoryNumber.value
                                )

                                ProtocolBuilder.clear()
                            }
                            CustomController.logMessages.clear()

                        }
                    }
                    LogsList()
                }
                if (viewModel.isDialog.value) {
                    TestDialog(
                        "Connect ONLY the high voltage alligator clip lead (XA1) to the test winding/UI terminal and the test lead (XA2) to the frame and/or parts being tested.",
                        true,
                        { viewModel.isDialog.value = false },
                        {
                            CustomController.isTestRunning.value = false
                            viewModel.isDialog.value = false
                        }
                    )
                }
                if (CustomController.isStartButton.value) {
                    TestDialog()
                }
            }
        )
    }
}