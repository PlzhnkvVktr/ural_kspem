package ru.avem.modules.tests.mgr

import androidx.compose.runtime.*
import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.avem.modules.tests.CustomController.logMessages
import ru.avem.viewmodels.MainScreenViewModel

class MGRViewModel(): ScreenModel{

    val nameMGR = MainScreenViewModel.TestEnum.nameMGR.testName

    var isDialog = mutableStateOf(false)
    var isPressStart = mutableStateOf(false)
    var waiting = mutableStateOf(true)
    val scope = CoroutineScope(Dispatchers.Default)

    val U: MutableState<String> = mutableStateOf("")
    var specifiedMgrU = mutableStateOf("")
    val R15: MutableState<String> = mutableStateOf("")
    val R60: MutableState<String> = mutableStateOf("")
    val kABS: MutableState<String> = mutableStateOf("")
    val tempAmb: MutableState<String> = mutableStateOf("")
    val tempTI: MutableState<String> = mutableStateOf("")

    val time: MutableState<String> = mutableStateOf("")
    val result: MutableState<String> = mutableStateOf("")

    fun clearFields() {
        scope.launch {
            logMessages.clear()
            U.value = ""
            specifiedMgrU.value = ""
            R15.value = ""
            R60.value = ""
            kABS.value = ""
            tempAmb.value = ""
            tempTI.value = ""
            time.value = ""
            result.value = ""
        }
    }

}