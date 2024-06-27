package ru.avem.modules.tests.viu

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.delay
import ru.avem.modules.common.logger.LogType
import ru.avem.modules.models.SelectedTestObject
import ru.avem.modules.tests.CustomController
import ru.avem.modules.tests.CustomController.ATR
import ru.avem.modules.tests.CustomController.appendMessageToLog
import ru.avem.modules.tests.CustomController.checkLatrZero
import ru.avem.modules.tests.CustomController.initARN
import ru.avem.modules.tests.CustomController.isTestRunning
import ru.avem.modules.tests.CustomController.latrEndsState
import ru.avem.modules.tests.CustomController.pr102
import ru.avem.modules.tests.CustomController.setVoltage
import ru.avem.modules.tests.CustomController.voltage
import ru.avem.modules.tests.Test
import ru.avem.viewmodels.TestScreenViewModel

suspend fun TestScreenViewModel.startMeasurementVIU(testItemLine: MutableState<MutableIterator<SelectedTestObject>>) {
    repeat(3) {
        if (isTestRunning.value and testItemLine.value.hasNext()) {
            Test.getCurrentTestObject(testItemLine, currentTest)

            setVoltage = Test.testObjectInfo.value?.u_viu.toString().toDouble()

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

            if (isTestRunning.value) initARN()

            if (isTestRunning.value) ATR.resetLATR()

            if (isTestRunning.value) checkLatrZero()

            if (isTestRunning.value) pr102.viu(true)

            if (isTestRunning.value) {
                val devTimer = System.currentTimeMillis()
                CustomController.appendMessageToLog("Повышение напряжения", LogType.MESSAGE)
                while ((voltage < setVoltage - 200) && isTestRunning.value) {
                    ATR.startUpLATRPulse(250f, 20f)
                    if (latrEndsState == 1) {
                        appendMessageToLog("Достигнуто максимальное напряжение АРН", LogType.ERROR)
                        isTestRunning.value = false
                        break
                    }
                    if (System.currentTimeMillis() - devTimer > 4000 && (CustomController.voltOnATR * 9.1) !in voltage * 0.6..voltage * 1.6) {
                        appendMessageToLog(
                            "${System.currentTimeMillis() - devTimer > 4000},${setVoltage}, ${CustomController.voltOnATR * 9.1}, ${voltage * 0.6} - ${voltage * 1.6}",
                            LogType.ERROR
                        )
                        appendMessageToLog(
                            "Несоответствие напряжение расчетного и измеренного напряжения ВИУ",
                            LogType.ERROR
                        )
                        isTestRunning.value = false
                    }
                    if (System.currentTimeMillis() - devTimer > 5000 && latrEndsState == 2) {
                        appendMessageToLog("Застревание АРН", LogType.ERROR)
                        isTestRunning.value = false
                    }
                    if (System.currentTimeMillis() - devTimer > 10000 && voltage < 15) {
                        appendMessageToLog(("no tension appeared"), LogType.ERROR)
                        isTestRunning.value = false
                    }
                    if (!isTestRunning.value) {
//                        viewModel.result.value = "Aborted"
                    }
                    Thread.sleep(100)
                }
            }


        } else {
            isTestRunning.value = false
        }
    }
}