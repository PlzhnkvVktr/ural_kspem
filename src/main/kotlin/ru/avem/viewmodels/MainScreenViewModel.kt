package ru.avem.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import ru.avem.db.DBManager
import ru.avem.modules.models.SelectedTestObject
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

    var testItemList = mutableListOf<SelectedTestObject>()
    var testItemLine = mutableStateOf(testItemList.iterator())

    val testMap: MutableMap<TestEnum, MutableState<Boolean>> = mutableMapOf(
        TestEnum.nameMGR to mutableStateOf(false),
        TestEnum.nameVIU to mutableStateOf(false),
        TestEnum.nameIKAS to mutableStateOf(false),
        TestEnum.nameHH to mutableStateOf(false),
        TestEnum.nameMV to mutableStateOf(false)
    )

    var typesTI = DBManager.getAllTI().ifEmpty { listOf("") }
    var selectedTI1 = mutableStateOf(typesTI.first())
    var selectedTI2 = mutableStateOf(typesTI.first())
    var selectedTI3 = mutableStateOf(typesTI.first())

    var card1 = mutableStateOf(true)
    var card2 = mutableStateOf(false)
    var card3 = mutableStateOf(false)

    var factoryNumber1 = mutableStateOf("")
    var factoryNumber2 = mutableStateOf("")
    var factoryNumber3 = mutableStateOf("")

    fun createTestItemList () {
        if (factoryNumber1.value.isNotEmpty()) testItemList.add(SelectedTestObject(1, factoryNumber1.value, selectedTI1.value))
        if (factoryNumber2.value.isNotEmpty()) testItemList.add(SelectedTestObject(2, factoryNumber2.value, selectedTI2.value))
        if (factoryNumber3.value.isNotEmpty()) testItemList.add(SelectedTestObject(3, factoryNumber3.value, selectedTI3.value))
        testItemLine.value = testItemList.iterator()
//        println(testItemLine.value.next())
    }

}
