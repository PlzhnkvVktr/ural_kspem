package ru.avem.modules.tests.hh

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.delay
import ru.avem.common.ProtocolBuilder
import ru.avem.db.DBManager
import ru.avem.modules.common.logger.LogType
import ru.avem.modules.devices.CM
import ru.avem.modules.models.SelectedTestObject
import ru.avem.modules.tests.CustomController
import ru.avem.modules.tests.CustomController.isTestRunning
import ru.avem.modules.tests.CustomController.pr102
import ru.avem.modules.tests.CustomController.pr66
import ru.avem.modules.tests.CustomController.statusMGR
import ru.avem.modules.tests.CustomController.testObject
import ru.avem.modules.tests.CustomController.testObjectName
import ru.avem.modules.tests.Test
import ru.avem.utils.getCurrentDate
import ru.avem.utils.getCurrentTime
import ru.avem.viewmodels.TestScreenViewModel
import ru.avem.modules.devices.avem.avem9.AVEM9Model
import java.lang.Thread.sleep
import kotlin.concurrent.thread

fun start(viewModel: TestScreenViewModel, testItemLine: MutableState<MutableIterator<SelectedTestObject>>) {
//    viewModel.clearFields()

    thread {
        println(testObjectName)
        println(testObject)
//        println(viewModel.name_1.value)
        isTestRunning.value = true
//        if (isTestRunning.value) initPR()
//        if (isTestRunning.value) initAVEM9()
//        if (isTestRunning.value) startMeasurementMGR(viewModel, testItemLine, viewModel.name_1, viewModel.specifiedMgrU_1)
//        if (isTestRunning.value) startMeasurementMGR(viewModel, testItemLine, viewModel.name_2, viewModel.specifiedMgrU_2)
//        if (isTestRunning.value) startMeasurementMGR(viewModel, testItemLine, viewModel.name_3, viewModel.specifiedMgrU_3)

    }

}

suspend fun startMeasurementMGR (
    viewModel: TestScreenViewModel,
    testItemLine: MutableState<MutableIterator<SelectedTestObject>>,
    name: MutableState<String>,
    specifiedMgrU: MutableState<String>
) {
    if (testItemLine.value.hasNext()) {

        Test.getCurrentTestObject(testItemLine, viewModel.currentTest)
        name.value = viewModel.currentTest.value?.factoryNumber.toString()
        specifiedMgrU.value = Test.testObjectInfo.value?.u_mgr.toString()

        testObjectName.value = viewModel.currentTest.value?.selectedTI.toString()
        testObject = DBManager.getTI(testObjectName.value)

        viewModel.isDialog.value = true
        while (viewModel.isDialog.value) {
            delay(200)
        }
        var timer = 300
        while (viewModel.isDialog.value && timer > 0) {
            delay(100)
            timer -= 1
        }
        if (viewModel.isDialog.value) isTestRunning.value = false


    } else {
        isTestRunning.value = false
    }

}


fun addReport() {
    with(ProtocolBuilder) {

    }
}