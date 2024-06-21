package ru.avem.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.avem.db.DBManager
import ru.avem.modules.common.logger.LogType
import ru.avem.modules.devices.CM
import ru.avem.modules.devices.avem.avem9.AVEM9Model
import ru.avem.modules.models.SelectedTestObject
import ru.avem.modules.models.TestItem
import ru.avem.modules.tests.CustomController
import ru.avem.modules.tests.CustomController.initAVEM9
import ru.avem.modules.tests.CustomController.initPR
import ru.avem.modules.tests.CustomController.isTestRunning
import ru.avem.modules.tests.Test
import ru.avem.modules.tests.mgr.startMeasurementMGR

class TestScreenViewModel: ScreenModel {

    val nameMGR = MainScreenViewModel.TestEnum.nameMGR.testName
    val nameIKAS = MainScreenViewModel.TestEnum.nameIKAS.testName
    val nameVIU = MainScreenViewModel.TestEnum.nameVIU.testName
    val nameHH = MainScreenViewModel.TestEnum.nameHH.testName
    val nameMV = MainScreenViewModel.TestEnum.nameMV.testName

    var isDialog = mutableStateOf(false)
    var isPressStart = mutableStateOf(false)
    var waiting = mutableStateOf(true)
    val scope = CoroutineScope(Dispatchers.Default)

    val currentTest = mutableStateOf<SelectedTestObject?>(null)

    val time: MutableState<String> = mutableStateOf("")
    val result: MutableState<String> = mutableStateOf("")

    val nameTest = MainScreenViewModel.TestEnum.nameIKAS.testName
    var warningUV = mutableStateOf(false)
    var warningVW = mutableStateOf(false)
    var warningWU = mutableStateOf(false)

    var Ruv1 = mutableStateOf("")
    var Rvw1 = mutableStateOf("")
    var Rwu1 = mutableStateOf("")
    var calcUV = mutableStateOf("")
    var calcVW = mutableStateOf("")
    var calcWU = mutableStateOf("")
    val tempAmb = mutableStateOf("")
    val tempTI = mutableStateOf("")
    val deviation = mutableStateOf("")

    val ikasI = mutableStateOf("")
    val ikasV = mutableStateOf("")


    val listTestItems = listOf(TestItem(), TestItem(), TestItem())

    fun start (testItemLine: MutableState<MutableIterator<SelectedTestObject>>) {
        scope.launch {
            isTestRunning.value = true
//            if (isTestRunning.value) initPR()
            startMeasurementMGR(testItemLine)
        }
    }

    fun startMeasurementMGR(testItemLine: MutableState<MutableIterator<SelectedTestObject>>) {
        if (isTestRunning.value) initAVEM9()
        repeat(3) {
            if (isTestRunning.value and testItemLine.value.hasNext()) {
                Test.getCurrentTestObject(testItemLine, currentTest)
                val idx = currentTest.value?.order
                listTestItems[idx!!].name.value = currentTest.value?.factoryNumber.toString()
                listTestItems[idx].specifiedMgrU.value = Test.testObjectInfo.value?.u_mgr.toString()

                CustomController.testObjectName.value = currentTest.value?.selectedTI.toString()
                CustomController.testObject = DBManager.getTI(CustomController.testObjectName.value)

                isDialog.value = true
                while (isDialog.value) {
                    Thread.sleep(200)
                }
                var timer = 300
                while (isDialog.value && timer > 0) {
                    Thread.sleep(100)
                    timer -= 1
                }
                if (isDialog.value) isTestRunning.value = false
                CM.startPoll(CM.DeviceID.PR66.name, CustomController.pr66.model.STATUS) { value ->
                    CustomController.statusMGR = value.toInt()
                }
                CM.startPoll(CM.DeviceID.PR66.name, CustomController.pr66.model.VOLTAGE) { value ->
                    listTestItems[idx].mgrU.value = value.toString()
                }
                CM.startPoll(CM.DeviceID.PR66.name, CustomController.pr66.model.R15_MEAS) { value ->
                    listTestItems[idx].R15.value = value.toString()
                }
                CM.startPoll(CM.DeviceID.PR66.name, CustomController.pr66.model.R60_MEAS) { value ->
                    listTestItems[idx].R60.value = value.toString()
                }
                CM.startPoll(CM.DeviceID.PR66.name, CustomController.pr66.model.ABSORPTION) { value ->
                    listTestItems[idx].kABS.value = value.toString()
                }
                if (isTestRunning.value) {
                    CustomController.pr102.viu1(true)
                    Thread.sleep(1000)
                }
                if (isTestRunning.value) {
                    CustomController.pr66.startMeasurement(
                        AVEM9Model.MeasurementMode.Resistance, when {
                            CustomController.testObject.u_mgr.toInt() == 2500 -> {
                                AVEM9Model.SpecifiedVoltage.V2500
                            }
                            CustomController.testObject.u_mgr.toInt() == 1000 -> {
                                AVEM9Model.SpecifiedVoltage.V1000
                            }
                            CustomController.testObject.u_mgr.toInt() == 500 -> {
                                AVEM9Model.SpecifiedVoltage.V500
                            }
                            else -> {
                                AVEM9Model.SpecifiedVoltage.V500
                            }
                        })
                    CustomController.appendMessageToLog("Идет измерение...", LogType.ERROR)
                }
                var time = 30
                while (isTestRunning.value && CustomController.statusMGR != 4 && time-- > 0) {
                    Thread.sleep(1000)
                }
            } else {
                isTestRunning.value = false
            }

        }
    }


}
