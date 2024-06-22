package ru.avem.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.registry.screenModule
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.launch
import ru.avem.db.DBManager
import ru.avem.enums.TestEnum
import ru.avem.modules.models.SelectedTestObject
import ru.avem.modules.tests.CustomController
import ru.avem.modules.tests.Test
import ru.avem.modules.tests.hh.HHScreen
import ru.avem.modules.tests.ikas.IKASScreen
import ru.avem.modules.tests.mgr.MGRScreen
import ru.avem.modules.tests.mv.MVScreen
import ru.avem.modules.tests.viu.VIUScreen

open class MainScreenViewModel : ScreenModel {
    var allCheckedButton: MutableState<Boolean> = mutableStateOf(false)
    var startTestButton: MutableState<Boolean> = mutableStateOf(false)

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

    fun checkboxClick (
        item: Map.Entry<TestEnum, MutableState<Boolean>>,
        found: Test?
    ) {
        if (found != null) {
            testList.remove(found)
        } else {
            checkTest(item.key)
        }

        testsLine.value = testList.iterator()
        startTestButton.value = testsLine.value.hasNext()
    }
    fun selectAll () {
        screenModelScope.launch {
            if (!allCheckedButton.value) {
                allCheckedButton.value = true
                testList.clear()
                testMap.forEach { item ->
                    item.value.value = true
                    checkTest(item.key)
                }
            } else {
                allCheckedButton.value = false
                testMap.forEach { item -> item.value.value = false }
                testList.clear()
            }
            testsLine.value = testList.iterator()
            startTestButton.value = testsLine.value.hasNext()
        }
    }

    fun startTests (navigator: Navigator) {
        screenModelScope.launch {
            createTestItemList()
            CustomController.testObjectName.value = selectedTI1.value
            CustomController.testObject = DBManager.getTI(CustomController.testObjectName.value)
            navigator.push(testsLine.value.next())
            testMap.forEach { item -> item.value.value = false }
            startTestButton.value = false
            allCheckedButton.value = false
        }
    }

    fun checkTest (item: TestEnum): Boolean {
        return when (item) {
            TestEnum.nameMGR -> testList.add(MGRScreen(this))
            TestEnum.nameVIU -> testList.add(VIUScreen(this))
            TestEnum.nameIKAS -> testList.add(IKASScreen(this))
            TestEnum.nameHH -> testList.add(HHScreen(this))
            TestEnum.nameMV -> testList.add(MVScreen(this))
        }
    }

    fun createTestItemList () {
        if (factoryNumber1.value.isNotEmpty()) testItemList.add(SelectedTestObject(0, factoryNumber1.value, selectedTI1.value))
        if (factoryNumber2.value.isNotEmpty()) testItemList.add(SelectedTestObject(1, factoryNumber2.value, selectedTI2.value))
        if (factoryNumber3.value.isNotEmpty()) testItemList.add(SelectedTestObject(2, factoryNumber3.value, selectedTI3.value))
        testItemLine.value = testItemList.iterator()
    }

}
