package ru.avem.modules.tests.mgr

import androidx.compose.runtime.MutableState
import ru.avem.common.ProtocolBuilder
import ru.avem.db.DBManager
import ru.avem.modules.common.logger.LogType
import ru.avem.modules.devices.CM
import ru.avem.modules.models.SelectedTestObject
import ru.avem.modules.tests.CustomController
import ru.avem.modules.tests.CustomController.isTestRunning
import ru.avem.modules.tests.CustomController.pr102
import ru.avem.modules.tests.CustomController.pr66
import ru.avem.modules.tests.CustomController.testObject
import ru.avem.modules.tests.CustomController.testObjectName
import ru.avem.modules.tests.Test
import ru.avem.utils.getCurrentDate
import ru.avem.utils.getCurrentTime
import ru.avem.viewmodels.TestScreenViewModel
import ru.avem.modules.devices.avem.avem9.AVEM9Model
import ru.avem.modules.tests.CustomController.initAVEM9
import ru.avem.modules.tests.CustomController.statusMGR
import java.lang.Thread.sleep
import kotlin.concurrent.thread


//fun TestScreenViewModel.startMeasurementMGR() {
//    if (isTestRunning.value) initAVEM9()
//    repeat(3) {
//        if (isTestRunning.value) {
////            startMeasurementMGR(CustomController.viewModel, testItemLine, viewModel.name_1, viewModel.specifiedMgrU_1)
//        }
//    }
//    if (isTestRunning.value) startMeasurementMGR(viewModel, testItemLine, viewModel.name_1, viewModel.specifiedMgrU_1)
//    if (isTestRunning.value) startMeasurementMGR(viewModel, testItemLine, viewModel.name_2, viewModel.specifiedMgrU_2)
//    if (isTestRunning.value) startMeasurementMGR(viewModel, testItemLine, viewModel.name_3, viewModel.specifiedMgrU_3)
//}

//fun start(viewModel: TestScreenViewModel, testItemLine: MutableState<MutableIterator<SelectedTestObject>>) {
////    viewModel.clearFields()
//
//
//    thread {
//        isTestRunning.value = true
////        if (isTestRunning.value) initPR()
////        if (isTestRunning.value) initAVEM9()
////        if (isTestRunning.value) startMeasurementMGR(viewModel, testItemLine, viewModel.name_1, viewModel.specifiedMgrU_1)
////        if (isTestRunning.value) startMeasurementMGR(viewModel, testItemLine, viewModel.name_2, viewModel.specifiedMgrU_2)
////        if (isTestRunning.value) startMeasurementMGR(viewModel, testItemLine, viewModel.name_3, viewModel.specifiedMgrU_3)
//
//    }

//}

fun startMeasurementMGR (
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

        val idx = viewModel.currentTest.value?.order

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

        CM.startPoll(CM.DeviceID.PR66.name, pr66.model.STATUS) { value ->
            statusMGR = value.toInt()
        }
        CM.startPoll(CM.DeviceID.PR66.name, pr66.model.VOLTAGE) { value ->
            viewModel.listTestItems[idx!!].mgrU.value = value.toString()
        }
        CM.startPoll(CM.DeviceID.PR66.name, pr66.model.R15_MEAS) { value ->
            viewModel.listTestItems[idx!!].R15.value = value.toString()
        }
        CM.startPoll(CM.DeviceID.PR66.name, pr66.model.R60_MEAS) { value ->
            viewModel.listTestItems[idx!!].R60.value = value.toString()
        }
        CM.startPoll(CM.DeviceID.PR66.name, pr66.model.ABSORPTION) { value ->
            viewModel.listTestItems[idx!!].kABS.value = value.toString()
        }

        if (isTestRunning.value) {
            pr102.viu1(true)
            sleep(1000)
        }

        if (isTestRunning.value) {
            pr66.startMeasurement(
                AVEM9Model.MeasurementMode.Resistance, when {
                    testObject.u_mgr.toInt() == 2500 -> {
                        AVEM9Model.SpecifiedVoltage.V2500
                    }
                    testObject.u_mgr.toInt() == 1000 -> {
                        AVEM9Model.SpecifiedVoltage.V1000
                    }
                    testObject.u_mgr.toInt() == 500 -> {
                        AVEM9Model.SpecifiedVoltage.V500
                    }
                    else -> {
                        AVEM9Model.SpecifiedVoltage.V500
                    }
                })
            CustomController.appendMessageToLog("Идет измерение...", LogType.ERROR)
        }
        var time = 30
        while (isTestRunning.value && statusMGR != 4 && time-- > 0) {
            sleep(1000)
        }

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