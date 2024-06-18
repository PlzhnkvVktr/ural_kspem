package ru.avem.modules.tests.mgr

import androidx.compose.runtime.MutableState
import ru.avem.common.ProtocolBuilder
import ru.avem.modules.models.SelectedTestObject
import ru.avem.modules.tests.CustomController
import ru.avem.modules.tests.CustomController.initPR
import ru.avem.modules.tests.CustomController.isTestRunning
import ru.avem.modules.tests.CustomController.testObject
import ru.avem.modules.tests.Test
import ru.avem.utils.getCurrentDate
import ru.avem.utils.getCurrentTime
import java.lang.Thread.sleep
import kotlin.concurrent.thread

fun start(viewModel: MGRViewModel, testItemLine: MutableState<MutableIterator<SelectedTestObject>>) {
    viewModel.clearFields()

    thread {
        isTestRunning.value = true
//        if (isTestRunning.value) initPR()
        if (isTestRunning.value) startMeasurement(viewModel, testItemLine, viewModel.name_1, viewModel.specifiedMgrU_1)
        if (isTestRunning.value) startMeasurement(viewModel, testItemLine, viewModel.name_2, viewModel.specifiedMgrU_2)
        if (isTestRunning.value) startMeasurement(viewModel, testItemLine, viewModel.name_3, viewModel.specifiedMgrU_3)

    }

}

fun startMeasurement (
    viewModel: MGRViewModel,
    testItemLine: MutableState<MutableIterator<SelectedTestObject>>,
    name: MutableState<String>,
    specifiedMgrU: MutableState<String>
) {
    if (testItemLine.value.hasNext()) {

        Test.getCurrentTestObject(testItemLine, viewModel.currentTest)
        name.value = viewModel.currentTest.value?.factoryNumber.toString()
        specifiedMgrU.value = Test.testObjectInfo.value?.u_mgr.toString()

        viewModel.isDialog.value = true
        while (viewModel.isDialog.value) {
            sleep(200)
        }
        var timer = 300
        while (viewModel.isDialog.value && timer > 0) {
            sleep(100)
            timer -= 1
        }
        if (viewModel.isDialog.value) isTestRunning.value = false


    } else {
        isTestRunning.value = false
    }

}


fun addReport(mgrVM: MGRViewModel) {
    with(ProtocolBuilder) {
//        U = mgrVM.U.value
//        R15 = mgrVM.R15.value
//        R60 = mgrVM.R60.value
//        kABS = mgrVM.kABS.value
        operator = CustomController.currentOperator.value
        date = getCurrentDate()
        time = getCurrentTime()
        mgrResult = mgrVM.result.value
    }
}