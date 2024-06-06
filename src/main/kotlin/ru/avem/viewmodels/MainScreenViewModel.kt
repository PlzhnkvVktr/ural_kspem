package ru.avem.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import ru.avem.db.DBManager
import ru.avem.modules.tests.Test

open class MainScreenViewModel : ScreenModel {
    var allCheckedButton: MutableState<Boolean> = mutableStateOf(false)
    var startTestButton: MutableState<Boolean> = mutableStateOf(false)

    enum class TestEnum(var testName: String) {
        nameMGR("Испытание изоляции обмоток относительно корпуса и между фазами обмотки на электрическую прочность"),
        nameVIU("Испытание изоляции обмоток относительно корпуса и между фазами обмотки на электрическую прочность."),
        nameIKAS("Измерение сопротивления обмоток постоянному току в практически холодном состоянии."),
        nameMV("Проверка правильности соединения обмоток и обнаружение витковых замыканий."),
        nameHH("Контроль равенства токов по фазам."),
    }

    var testList = mutableListOf<Test>()
    var testsLine = mutableStateOf(testList.iterator())

    val testMap: MutableMap<TestEnum, MutableState<Boolean>> = mutableMapOf(
        TestEnum.nameMGR to mutableStateOf(false),
        TestEnum.nameVIU to mutableStateOf(false),
        TestEnum.nameIKAS to mutableStateOf(false),
        TestEnum.nameHH to mutableStateOf(false),
        TestEnum.nameMV to mutableStateOf(false)
    )

    var typesTI = DBManager.getAllTI().ifEmpty { listOf("") }
    var selectedTI = mutableStateOf(typesTI.first())

    var factoryNumber = mutableStateOf("")

}
