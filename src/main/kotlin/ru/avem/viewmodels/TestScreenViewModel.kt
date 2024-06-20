package ru.avem.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.avem.modules.models.SelectedTestObject
import ru.avem.modules.tests.CustomController
import ru.avem.modules.tests.CustomController.initAVEM9
import ru.avem.modules.tests.CustomController.initPR
import ru.avem.modules.tests.CustomController.isTestRunning

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

    val name_1 = mutableStateOf("")
    var specifiedMgrU_1 = mutableStateOf("")
    val mgrU_1: MutableState<String> = mutableStateOf("")
    val R15_1: MutableState<String> = mutableStateOf("")
    val R60_1: MutableState<String> = mutableStateOf("")
    val kABS_1: MutableState<String> = mutableStateOf("")

    val name_2 = mutableStateOf("")
    var specifiedMgrU_2 = mutableStateOf("")
    val mgrU_2: MutableState<String> = mutableStateOf("")
    val R15_2: MutableState<String> = mutableStateOf("")
    val R60_2: MutableState<String> = mutableStateOf("")
    val kABS_2: MutableState<String> = mutableStateOf("")

    val name_3 = mutableStateOf("")
    var specifiedMgrU_3 = mutableStateOf("")
    val mgrU_3: MutableState<String> = mutableStateOf("")
    val R15_3: MutableState<String> = mutableStateOf("")
    val R60_3: MutableState<String> = mutableStateOf("")
    val kABS_3: MutableState<String> = mutableStateOf("")

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

//    val time = mutableStateOf("")
//    val result = mutableStateOf("")

    fun start () {
        screenModelScope.launch {
            isTestRunning.value = true
            if (CustomController.isTestRunning.value) initPR()
        }
    }

    fun startMeasurementMGR () {
        if (isTestRunning.value) initAVEM9()
    }


}