package ru.avem.modules.tests.mgr

import androidx.compose.runtime.*
import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.avem.modules.models.SelectedTestObject
import ru.avem.modules.tests.CustomController.logMessages
import ru.avem.modules.tests.Test
import ru.avem.viewmodels.MainScreenViewModel

class MGRViewModel(): ScreenModel {

    val nameMGR = MainScreenViewModel.TestEnum.nameMGR.testName

    var isDialog = mutableStateOf(false)
    var isPressStart = mutableStateOf(false)
    var waiting = mutableStateOf(true)
    val scope = CoroutineScope(Dispatchers.Default)

    val currentTest = mutableStateOf<SelectedTestObject?>(null)

    var specifiedMgrU_1 = mutableStateOf("")
    val name_1 = mutableStateOf("")
    val U_1: MutableState<String> = mutableStateOf("")
    val R15_1: MutableState<String> = mutableStateOf("")
    val R60_1: MutableState<String> = mutableStateOf("")
    val kABS_1: MutableState<String> = mutableStateOf("")

    var specifiedMgrU_2 = mutableStateOf("")
    val name_2 = mutableStateOf("")
    val U_2: MutableState<String> = mutableStateOf("")
    val R15_2: MutableState<String> = mutableStateOf("")
    val R60_2: MutableState<String> = mutableStateOf("")
    val kABS_2: MutableState<String> = mutableStateOf("")

    var specifiedMgrU_3 = mutableStateOf("")
    val name_3 = mutableStateOf("")
    val U_3: MutableState<String> = mutableStateOf("")
    val R15_3: MutableState<String> = mutableStateOf("")
    val R60_3: MutableState<String> = mutableStateOf("")
    val kABS_3: MutableState<String> = mutableStateOf("")

    val time: MutableState<String> = mutableStateOf("")
    val result: MutableState<String> = mutableStateOf("")

//    fun getTestObject (testItemLine: MutableState<MutableIterator<SelectedTestObject>>) {
//        currentTest.value = testItemLine.value.next()
//    }

    fun clearFields() {
        scope.launch {
            logMessages.clear()
//            specifiedMgrU.value = ""

            time.value = ""
            result.value = ""
        }
    }

}