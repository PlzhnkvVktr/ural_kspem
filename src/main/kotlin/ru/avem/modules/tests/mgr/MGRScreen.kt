package ru.avem.modules.tests.mgr

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.sharp.*
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
import ru.avem.modules.tests.Test
import ru.avem.components.LogsList
import ru.avem.screens.MainScreen
import ru.avem.viewmodels.MainScreenViewModel
import ru.avem.components.SpecifiedParamsList
import ru.avem.db.DBManager.addNewProtocol
import ru.avem.modules.tests.CustomController.isStartButton
import ru.avem.modules.tests.CustomController.isTestRunning
import ru.avem.modules.tests.CustomController.logMessages
import ru.avem.modules.tests.CustomController.testObject
import kotlin.concurrent.thread


class MGRScreen(private var mainViewModel: MainScreenViewModel) : Test() {
    override val testName: String
        get() = TestEnum.nameMGR.testName

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { MGRViewModel() }
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val navigator = LocalNavigator.currentOrThrow

        LifecycleEffect(onStarted = {
            viewModel.clearFields()
            getTestObject(mainViewModel.testItemLine)
            getCurrentTestObject()
            viewModel.currentTest.value = currentTestObject.value
        })

        Column {
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
                            SpecifiedParamsList(testObjectInfo)
                            Column(
                                modifier = Modifier.fillMaxHeight(0.6f).fillMaxWidth(0.8f).border(1.dp, Color.LightGray)
                            ) {
                                MGRTestWindow(viewModel)
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
                                        Thread.sleep(1000)
                                        viewModel.waiting.value = true
                                    }
                                } else {
                                    isTestRunning.value = false
                                    thread {
                                        viewModel.waiting.value = false
                                        Thread.sleep(1000)
                                        viewModel.waiting.value = true
                                    }
                                }
                            }
                            ActionButton("next", Icons.Filled.ArrowForward, !isTestRunning.value) {
                                if (mainViewModel.testsLine.value.hasNext()) {
//                                    addReport(viewModel, mainViewModel.factoryNumber.value)
                                    navigator.replace(mainViewModel.testsLine.value.next())
                                } else {
//                                    addReport(viewModel, mainViewModel.factoryNumber.value)
                                    mainViewModel.testList.clear()
                                    navigator.replace(MainScreen())

                                    addNewProtocol(testObject.name, testObject, mainViewModel.factoryNumber1.value)

                                    ProtocolBuilder.clear()
                                }
                                logMessages.clear()

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
                                isTestRunning.value = false
                                viewModel.isDialog.value = false
                            }
                        )
                    }
                    if (isStartButton.value) {
                        TestDialog()
                    }
                },
                bottomBar = { }
            )
        }
    }
}
