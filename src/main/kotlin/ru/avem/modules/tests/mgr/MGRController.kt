package ru.avem.modules.tests.mgr

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.delay
import ru.avem.db.DBManager
import ru.avem.modules.common.logger.LogType
import ru.avem.modules.devices.CM
import ru.avem.modules.models.SelectedTestObject
import ru.avem.modules.tests.CustomController.isTestRunning
import ru.avem.modules.tests.CustomController.pr102
import ru.avem.modules.tests.CustomController.pr66
import ru.avem.modules.tests.CustomController.testObject
import ru.avem.modules.tests.CustomController.testObjectName
import ru.avem.modules.tests.Test
import ru.avem.viewmodels.TestScreenViewModel
import ru.avem.modules.devices.avem.avem9.AVEM9Model
import ru.avem.modules.tests.CustomController.appendMessageToLog
import ru.avem.modules.tests.CustomController.initAVEM9
import ru.avem.modules.tests.CustomController.statusMGR

//class MeasurementMGR() : Test() {
//
//}
suspend fun TestScreenViewModel.startMeasurementMGR(testItemLine: MutableState<MutableIterator<SelectedTestObject>>) {
    repeat(3) {
        if (isTestRunning.value) initAVEM9()
        if (isTestRunning.value and testItemLine.value.hasNext()) {
            Test.getCurrentTestObject(testItemLine, currentTest)
            val idx = currentTest.value?.order
            listTestItems[idx!!].name.value = currentTest.value?.factoryNumber.toString()
            listTestItems[idx].specifiedMgrU.value = Test.testObjectInfo.value?.u_mgr.toString()

            testObjectName.value = currentTest.value?.selectedTI.toString()
            testObject = DBManager.getTI(testObjectName.value)

            isDialog.value = true
            while (isDialog.value) {
                delay(200)
            }
            var timer = 300
            while (isDialog.value && timer > 0) {
                delay(100)
                timer -= 1
            }
            if (isDialog.value) isTestRunning.value = false
            CM.startPoll(CM.DeviceID.PR66.name, pr66.model.STATUS) { value ->
                statusMGR = value.toInt()
            }
            CM.startPoll(CM.DeviceID.PR66.name, pr66.model.VOLTAGE) { value ->
                listTestItems[idx].mgrU.value = value.toString()
            }
            CM.startPoll(CM.DeviceID.PR66.name, pr66.model.R15_MEAS) { value ->
                listTestItems[idx].r15.value = value.toString()
            }
            CM.startPoll(CM.DeviceID.PR66.name, pr66.model.R60_MEAS) { value ->
                listTestItems[idx].r60.value = value.toString()
            }
            CM.startPoll(CM.DeviceID.PR66.name, pr66.model.ABSORPTION) { value ->
                listTestItems[idx].kABS.value = value.toString()
            }
            if (isTestRunning.value) {
                pr102.viu1(true)
                delay(1000)
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
                    }
                )
                appendMessageToLog("Идет измерение...", LogType.ERROR)
            }
            var time = 30
            while (isTestRunning.value && statusMGR != 4 && time-- > 0) {
                delay(1000)
            }
            appendMessageToLog("Измерение завершено", LogType.MESSAGE)
        } else {
            isTestRunning.value = false
            appendMessageToLog("Измерение прервано", LogType.ERROR)
        }
    }
}