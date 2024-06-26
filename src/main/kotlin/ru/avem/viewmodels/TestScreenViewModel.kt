package ru.avem.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.avem.enums.TestEnum
import ru.avem.modules.models.SelectedTestObject
import ru.avem.modules.models.TestItem
import ru.avem.modules.tests.CustomController.initButtonPost
import ru.avem.modules.tests.CustomController.initPR
import ru.avem.modules.tests.CustomController.isTestRunning
import ru.avem.modules.tests.hh.startMeasurementHH
import ru.avem.modules.tests.ikas.startMeasurementIKAS
import ru.avem.modules.tests.mgr.startMeasurementMGR
import ru.avem.modules.tests.mv.startMeasurementMV
import ru.avem.modules.tests.viu.startMeasurementVIU

class TestScreenViewModel : ScreenModel {

    val nameMGR = TestEnum.nameMGR.testName
    val nameIKAS = TestEnum.nameIKAS.testName
    val nameVIU = TestEnum.nameVIU.testName
    val nameHH = TestEnum.nameHH.testName
    val nameMV = TestEnum.nameMV.testName

    var isDialog = mutableStateOf(false)
    var isPressStart = mutableStateOf(false)

    var waiting = mutableStateOf(true)

    val loggerScope = CoroutineScope(Dispatchers.Main)

    val currentTest = mutableStateOf<SelectedTestObject?>(null)

    val time: MutableState<String> = mutableStateOf("")
    val result: MutableState<String> = mutableStateOf("")

    val nameTest = TestEnum.nameIKAS.testName
    var warningUV = mutableStateOf(false)
    var warningVW = mutableStateOf(false)
    var warningWU = mutableStateOf(false)

    var listTestItems = listOf(TestItem(), TestItem(), TestItem())

    fun start (
        testItemLine: MutableState<MutableIterator<SelectedTestObject>>,
        testName: TestEnum
    ) {
        screenModelScope.launch(
            Dispatchers.Default
        ) {
//            waiting.value = false
            isTestRunning.value = true
//            if (isTestRunning.value) initPR()
//            if (isTestRunning.value) initButtonPost()
//            waiting.value = true
            when (testName) {
                TestEnum.nameMGR -> startMeasurementMGR(testItemLine)
                TestEnum.nameVIU -> startMeasurementVIU(testItemLine)
                TestEnum.nameIKAS -> startMeasurementIKAS(testItemLine)
                TestEnum.nameHH -> startMeasurementHH(testItemLine)
                TestEnum.nameMV -> startMeasurementMV(testItemLine)
            }
        }
    }

    fun clearFields () {
        listTestItems = listOf(TestItem(), TestItem(), TestItem())
    }


}
