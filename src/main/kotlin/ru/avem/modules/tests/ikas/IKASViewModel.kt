package ru.avem.modules.tests.ikas

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.avem.enums.TestEnum
import ru.avem.modules.tests.CustomController
import ru.avem.modules.tests.CustomController.logMessages
import ru.avem.viewmodels.MainScreenViewModel

class IKASViewModel() : ScreenModel {

    val nameTest = TestEnum.nameIKAS.testName
    var warningUV = mutableStateOf(false)
    var warningVW = mutableStateOf(false)
    var warningWU = mutableStateOf(false)
    var waiting = mutableStateOf(true)
    val scope = CoroutineScope(Dispatchers.Default)
    var isDialog = mutableStateOf(false)

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

    val time = mutableStateOf("")
    val result = mutableStateOf("")

    fun clearFields() {
        scope.launch {
            logMessages.clear()
            Ruv1.value = ""
            Rvw1.value = ""
            Rwu1.value = ""
            calcUV.value = ""
            calcVW.value = ""
            calcWU.value = ""
            result.value = ""
            tempAmb.value = ""
            tempTI.value = ""
        }
    }

}