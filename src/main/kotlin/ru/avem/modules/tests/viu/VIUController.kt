package ru.avem.modules.tests.viu

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.delay
import ru.avem.common.af
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
import ru.avem.modules.tests.utils.sleepWhileRun
import ru.avem.viewmodels.TestScreenViewModel
import kotlin.math.abs

suspend fun TestScreenViewModel.startMeasurementVIU(testItemLine: MutableState<MutableIterator<SelectedTestObject>>) {
    repeat(3) {
        if (isTestRunning.value and testItemLine.value.hasNext()) {
            Test.getCurrentTestObject(testItemLine, currentTest)
            val idx = currentTest.value!!.order

            setVoltage = Test.testObjectInfo.value!!.u_viu.toDouble()
            val setTime = Test.testObjectInfo.value!!.t_viu.toDouble()

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
                appendMessageToLog("Повышение напряжения", LogType.MESSAGE)
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
                        listTestItems[idx].res_viu.value = "Испытание прервано"
                    }
                    delay(100)
                }
                while ((voltage < setVoltage - 10) && isTestRunning.value) {
                    ATR.startUpLATRPulse(250f, 13f)
                    Thread.sleep(100)
                    if (latrEndsState == 1) {
                        appendMessageToLog("Максимальное напряжение АРН", LogType.ERROR)
                        isTestRunning.value = false
                        break
                    }
                    if ((CustomController.voltOnATR * 9.1) !in voltage * 0.7..voltage * 1.6) {
                        appendMessageToLog(
                            "Несоответствие напряжение расчетного и измеренного напряжения ВИУ",
                            LogType.ERROR
                        )
                        isTestRunning.value = false
                    }
                }
                ATR.stopLATR()
                if (!isTestRunning.value) {
                    listTestItems[idx].res_viu.value = "Испытание прервано"
                }
                delay(1000)
            }

            if (isTestRunning.value) {
                appendMessageToLog("Выдержка напряжения", LogType.MESSAGE)
                var timer = setTime
                while (isTestRunning.value && timer > 0) {
                    Thread.sleep(100)
                    timer -= 0.1
                    listTestItems[idx].t_viu.value = abs(timer).af()
                }
                if (isTestRunning.value) {
                    listTestItems[idx].res_viu.value = "Успешно"
                } else {
                    listTestItems[idx].res_viu.value = "Прервано"
                }
//                addReport(viewModel)


                sleepWhileRun(1.0)
                ATR.resetLATR()

                while (voltage > 200) {
                    delay(100)
                }

                delay(1000)
            }

        } else {
            isTestRunning.value = false
        }
    }
}