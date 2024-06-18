package ru.avem.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.avem.db.DBManager

class EditorOIScreenViewModel : ScreenModel {
    val change = { newScheme.value = !newScheme.value }
    private val scope = CoroutineScope(Dispatchers.IO)

    var typesTI = DBManager.getAllTI().ifEmpty { listOf("") }
    var selectedTI = mutableStateOf(typesTI.first())

    var addNewTI: MutableState<Boolean> = mutableStateOf(false)
    var validateField1 = mutableStateOf(false)
    var validateField2 = mutableStateOf(false)
    var validateField3 = mutableStateOf(false)
    var validateField4 = mutableStateOf(false)
    var validateField5 = mutableStateOf(false)
    var validateField6 = mutableStateOf(false)
    var validateField7 = mutableStateOf(false)
    var validateField8 = mutableStateOf(false)
    var validateField9 = mutableStateOf(false)
    var validateField10 = mutableStateOf(false)
    var validateField11 = mutableStateOf(false)
    var validateField12 = mutableStateOf(false)
    var validateField13 = mutableStateOf(false)
    var validateField14 = mutableStateOf(false)
    var validateField15 = mutableStateOf(false)

    // текущие параметры
    var name = mutableStateOf("")
    var scheme = mutableStateOf(true)
    var power = mutableStateOf("")
    var u_linear = mutableStateOf("")
    var i = mutableStateOf("")
    var i_viu = mutableStateOf("")
    var i_mz = mutableStateOf("")
    var u_viu = mutableStateOf("")
    var u_mgr = mutableStateOf("")
    var t_viu = mutableStateOf("")
    var t_hh = mutableStateOf("")
    var t_mv = mutableStateOf("")
    var r_max = mutableStateOf("")
    var r_min = mutableStateOf("")
    var r20_max = mutableStateOf("")
    var r20_min = mutableStateOf("")
    var t = mutableStateOf("")


    // новые параметры
    var newName = mutableStateOf("")
    var newScheme = mutableStateOf(true)
    var newPower = mutableStateOf("")
    var newU_linear = mutableStateOf("")
    var newI = mutableStateOf("")
    var newI_viu = mutableStateOf("")
    var newI_mz = mutableStateOf("")
    var newU_viu = mutableStateOf("")
    var newU_mgr = mutableStateOf("")
    var newT_viu = mutableStateOf("")
    var newT_hh = mutableStateOf("")
    var newT_mv = mutableStateOf("")
    var newR_max = mutableStateOf("")
    var newR_min = mutableStateOf("")
    var newR20_max = mutableStateOf("")
    var newR20_min = mutableStateOf("")
    var newT = mutableStateOf("")

    var dialogWindow = mutableStateOf(false)
    fun openDialog () {
        scope.launch{
            dialogWindow.value = true
            delay(1500)
            dialogWindow.value = false
        }
    }

    fun deleteTI () {
        scope.launch {
            DBManager.deleteTestItemById(selectedTI.value)
            typesTI = DBManager.getAllTI().ifEmpty { listOf("") }
            selectedTI.value = typesTI.first()
        }
    }

    fun addNewTI () {
        scope.launch {
            if (!addNewTI.value) {
                DBManager.deleteTestItemById(selectedTI.value)
                DBManager.addTI(
                    name.value,
                    scheme.value,
                    power.value,
                    u_linear.value,
                    i.value,
                    i_viu.value,
                    i_mz.value,
                    u_viu.value,
                    u_mgr.value,
                    t_viu.value,
                    t_hh.value,
                    t_mv.value,
                    r_max.value,
                    r_min.value,
                    r20_max.value,
                    r20_min.value,
                    t.value
                )
            } else {
                DBManager.addTI(
                    newName.value,
                    newScheme.value,
                    newPower.value,
                    newU_linear.value,
                    newI.value,
                    newI_viu.value,
                    newI_mz.value,
                    newU_viu.value,
                    newU_mgr.value,
                    newT_viu.value,
                    newT_hh.value,
                    newT_mv.value,
                    newR_max.value,
                    newR_min.value,
                    newR20_max.value,
                    newR20_min.value,
                    newT.value
                )
            }
            addNewTI.value = false
            typesTI = DBManager.getAllTI().ifEmpty { listOf("") }
            selectedTI.value = typesTI.first()
        }
    }

}