package ru.avem.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.*
import ru.avem.enums.TestEnum
import ru.avem.modules.models.SelectedTestObject
import ru.avem.modules.models.TestItem
import ru.avem.modules.tests.CustomController.initPR
import ru.avem.modules.tests.CustomController.isTestRunning
import ru.avem.modules.tests.mgr.startMeasurementMGR

class TestScreenViewModel: ScreenModel {

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

    fun start(
        testItemLine: MutableState<MutableIterator<SelectedTestObject>>,
        testName: TestEnum
    ) {
        screenModelScope.launch {
            waiting.value = false
            delay(1000)
            waiting.value = true
            cancel()
        }
        screenModelScope.launch(
            Dispatchers.Default,
            CoroutineStart.DEFAULT
        ) {
            isTestRunning.value = true
            if (isTestRunning.value) initPR()
            delay(500)
            when (testName) {
                TestEnum.nameMGR -> startMeasurementMGR(testItemLine)
                TestEnum.nameVIU -> startMeasurementMGR(testItemLine)
                TestEnum.nameIKAS -> startMeasurementMGR(testItemLine)
                TestEnum.nameHH -> startMeasurementMGR(testItemLine)
                TestEnum.nameMV -> startMeasurementMGR(testItemLine)
            }

        }
//        screenModelScope.cancel()
    }

}
