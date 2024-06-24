package ru.avem.modules.tests.viu

import androidx.compose.runtime.MutableState
import ru.avem.modules.models.SelectedTestObject
import ru.avem.modules.tests.CustomController
import ru.avem.modules.tests.CustomController.initARN
import ru.avem.modules.tests.Test
import ru.avem.viewmodels.TestScreenViewModel

suspend fun TestScreenViewModel.startMeasurementVIU(testItemLine: MutableState<MutableIterator<SelectedTestObject>>) {
    repeat(3) {
        if (CustomController.isTestRunning.value and testItemLine.value.hasNext()) {
            Test.getCurrentTestObject(testItemLine, currentTest)

            initARN()

        } else {
            CustomController.isTestRunning.value = false
        }
    }
}