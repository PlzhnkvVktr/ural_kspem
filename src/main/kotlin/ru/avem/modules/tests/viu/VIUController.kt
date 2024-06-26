package ru.avem.modules.tests.viu

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.delay
import ru.avem.modules.models.SelectedTestObject
import ru.avem.modules.tests.CustomController
import ru.avem.modules.tests.CustomController.checkLatrZero
import ru.avem.modules.tests.CustomController.initARN
import ru.avem.modules.tests.CustomController.isTestRunning
import ru.avem.modules.tests.CustomController.pr102
import ru.avem.modules.tests.Test
import ru.avem.viewmodels.TestScreenViewModel

suspend fun TestScreenViewModel.startMeasurementVIU(testItemLine: MutableState<MutableIterator<SelectedTestObject>>) {
    repeat(3) {
        if (isTestRunning.value and testItemLine.value.hasNext()) {
            Test.getCurrentTestObject(testItemLine, currentTest)

            isDialog.value = true
            while (isDialog.value) {
                delay(200)
            }
            var timer = 300
            while (isDialog.value && timer > 0) {
                delay(100)
                timer -= 1
            }
            if (isDialog.value) isTestRunning.value = false

            if (isTestRunning.value) {
                initARN()
                delay(1000)
                checkLatrZero()
            }


            if (isTestRunning.value) pr102.viu(true)




        } else {
            isTestRunning.value = false
        }
    }
}