package ru.avem.modules.tests

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.screen.Screen
import ru.avem.db.DBManager
import ru.avem.db.TestItem
import ru.avem.modules.models.SelectedTestObject
import ru.avem.viewmodels.MainScreenViewModel

//interface Test : Screen {
//    val testName: String
//}

abstract class Test (

): Screen, MainScreenViewModel() {
    open val testName: String = ""
    val currentTestObject = mutableStateOf<SelectedTestObject?>(null)
    val testObjectInfo = mutableStateOf<TestItem?>(null)

    fun getTestObject(testItemLine: MutableState<MutableIterator<SelectedTestObject>>) {
        currentTestObject.value = testItemLine.value.next()
    }
    fun getCurrentTestObject() {
        testObjectInfo.value = currentTestObject.value?.let { DBManager.getTI(it.selectedTI) }
    }
}
