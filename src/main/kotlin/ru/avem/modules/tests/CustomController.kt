package ru.avem.modules.tests

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.launch
import ru.avem.common.af
import ru.avem.common.repos.AppConfig
import ru.avem.db.DBManager
import ru.avem.db.TestItem
import ru.avem.modules.common.logger.LogMessage
import ru.avem.modules.common.logger.LogType
import ru.avem.modules.devices.CM
import ru.avem.modules.devices.avem.atr.ATR
import ru.avem.modules.devices.avem.atr.ATRModel
import ru.avem.modules.devices.avem.avem4.AVEM4
import ru.avem.modules.devices.avem.avem7.AVEM7
import ru.avem.modules.devices.avem.ikas10.IKAS10
import ru.avem.modules.devices.bris.m4122.M4122
import ru.avem.modules.devices.owen.pr.PR
import ru.avem.modules.devices.owen.th01.TH01
import ru.avem.modules.devices.owen.th01.TH01Model
import ru.avem.modules.devices.owen.trm202.TRM202
import ru.avem.modules.devices.owen.trm202.TRM202Model
import ru.avem.modules.devices.pm130.PM130
import ru.avem.modules.devices.pm130.PM130Model
import ru.avem.modules.tests.utils.ms
import ru.avem.modules.tests.utils.toDoubleOrDefault
import java.lang.Thread.sleep
import java.text.SimpleDateFormat
import kotlin.concurrent.thread
import kotlin.experimental.and
import kotlin.math.abs

object CustomController {
    val pr102 = CM.getDeviceByID<PR>(CM.DeviceID.DD2_1)
    val pm130 = CM.getDeviceByID<PM130>(CM.DeviceID.PAV41)
    val ATR = CM.getDeviceByID<ATR>(CM.DeviceID.GV240)
    val pv24 = CM.getDeviceByID<AVEM4>(CM.DeviceID.PV24)
    val pa62 = CM.getDeviceByID<AVEM7>(CM.DeviceID.PA62)
    val pv61 = CM.getDeviceByID<AVEM4>(CM.DeviceID.PV61)

    @Volatile
    var isTestRunning = mutableStateOf(false)

    val currentOperator = mutableStateOf(AppConfig.params.login)

    var isStartPressed = mutableStateOf(false)
    var isStopPressed = mutableStateOf(false)
    var doorZone = mutableStateOf(false)
    var doorSCO = mutableStateOf(false)
    var ikzTI = mutableStateOf(false)
    var ikzVIU = mutableStateOf(false)

    var voltOnATR = 1.0
    var ktrVoltage = 1.0
    var voltAverage = 0.0
    var ktrAmperage = 1.0

    val logMessages = mutableStateListOf<LogMessage>()
    var isStartButton = mutableStateOf(false)

    var testObjectName = mutableStateOf("")
    lateinit var testObject: TestItem

    fun appendMessageToLog(text: String, type: LogType = LogType.DEBUG) {
        val msg = "${SimpleDateFormat("HH:mm:ss").format(ms())} | $text"
        logMessages.add(LogMessage(msg, type))
    }

    fun initWatchDogDD2() {
        with(pr102) {
            initWithoutProtections()
            offAllKMs()
            CM.addWritingRegister(name, pr102.model.CMD, 1.toShort())
            CM.startPoll(name, pr102.model.STATE) { }
        }
    }

    fun initButtonPost() {
        isStartButton.value = true
        var i = 0
        while (!isStartPressed.value && isTestRunning.value && pr102.isResponding) {
            sleep(100)

            if (isStartPressed.value && isStartButton.value) {
                isStartButton.value = false
                break
            }
            if (!isStopPressed.value) {
                isStartButton.value = false
                isTestRunning.value = false
                appendMessageToLog("stop", LogType.ERROR)
            }
        }
    }

    fun checkLatrZero() {
        var latrTimer = 300
        while (voltOnATR > 10 && isTestRunning.value) {
            sleep(100)
            latrTimer--
            if (latrTimer<0) {
                appendMessageToLog("U ATR > 10", LogType.ERROR)
                isTestRunning.value = false
            }
        }
    }

    fun stopTestRunning() {
        isTestRunning.value = false
        CM.clearPollingRegisters()
        isStartButton.value = false
        pr102.offAllKMs()
        CM.clearWritingRegisters()
        voltOnATR = 1.0
        ktrVoltage = 1.0
        voltAverage = 0.0
        ktrAmperage = 1.0
    }

    fun initPR() {
        pr102.checkResponsibility()
        appendMessageToLog("${("Initialization")} БСУ...", LogType.MESSAGE)
        isStartPressed.value = false
        isStopPressed.value = false
        var stateLock = false
        var count = 0
        if (!pr102.isResponding) {
            appendMessageToLog("ПР102 ${("does not respond")}", LogType.ERROR)
            isTestRunning.value = false
        } else {
            pr102.offAllKMs()
            initWatchDogDD2()
            sleep(1000)
            CM.startPoll(CM.DeviceID.DD2_1.name, pr102.model.DI_01_16_RAW) { value ->
                isStopPressed.value = value.toShort() and 1 < 1   // 1
                isStartPressed.value = value.toShort() and 2 < 1   // 2
                doorZone.value = value.toShort() and 4 < 4   // 3
//                             = value.toShort() and 8 > 0   // 4
                doorSCO.value = value.toShort() and 16 < 16  // 5
                ikzTI.value = value.toShort() and 32 < 1 || stateLock // 6
                ikzVIU.value = value.toShort() and 64 < 1  // 7
//                             = value.toShort() and 128 > 0 // 8
            }
            sleep(1000)
            thread(isDaemon = true) {
                while (isTestRunning.value) {
                    sleep(100)
                    if (!pr102.isResponding) {
                        appendMessageToLog("ПР102 ${("does not respond")}", LogType.ERROR)
                        isTestRunning.value = false
                    }
                    if (!isStopPressed.value) {
                        appendMessageToLog(("the <STOP> button is pressed"), LogType.ERROR)
                        isTestRunning.value = false
                    }
                    if (doorZone.value) {
                        appendMessageToLog(("zone doors open"), LogType.ERROR)
                        isTestRunning.value = false
                    }
                    if (doorSCO.value) {
                        appendMessageToLog(("the doors are open"), LogType.ERROR)
                        isTestRunning.value = false
                    }
                    if (ikzTI.value) {
                        count++
                        if (count >= 8) {
                            appendMessageToLog(("Overcurrent"), LogType.ERROR)
                            stateLock = true
                            isTestRunning.value = false
                        }
                    } else {
                        count = 0
                    }
                    if (ikzVIU.value) {
                        appendMessageToLog(("Overcurrent"), LogType.ERROR)
                        isTestRunning.value = false
                    }
                    if (!isTestRunning.value) {
                        appendMessageToLog(("Test finished"), LogType.ERROR)
                    }
                }
                stopTestRunning()
            }
        }
    }

    fun initVibro(pol: MutableState<String>, rab: MutableState<String>) {
        CM.startPoll(CM.DeviceID.DD2_1.name, pr102.model.VIBRO_POL) { rab.value = it.af() }
        CM.startPoll(CM.DeviceID.DD2_1.name, pr102.model.VIBRO_RAB) { pol.value = it.af() }
    }

//    fun checkRPM(n: MutableState<String>) {
//        if (n.value.toDouble() > testObject.specifiedRPM.toDouble() / 2) {
//            appendMessageToLog(("Waiting for engine to stop"), LogType.ERROR)
//            while (isTestRunning.value && n.value.toDouble() > testObject.specifiedRPM.toDouble() / 2) {
//                sleep(1000)
//            }
//        }
//    }

    fun initARN() {
        appendMessageToLog("${("Initialization")} ATP...", LogType.MESSAGE)
        ATR.checkResponsibility()
        sleep(1000)

        if (ATR.isResponding) {
            CM.startPoll(CM.DeviceID.GV240.name, ATRModel.U_RMS_REGISTER) { value ->
                voltOnATR = value.toDouble()
                if (!ATR.isResponding) {
                    appendMessageToLog("АРН ${("does not respond")}", LogType.ERROR)
                    isTestRunning.value = false
                }
            }
        } else {
            appendMessageToLog("АРН ${("does not respond")}", LogType.ERROR)
            isTestRunning.value = false
        }
    }

    fun initPM130(
        Uuv: MutableState<String>? = null,
        Uvw: MutableState<String>? = null,
        Uwu: MutableState<String>? = null,
        Iu: MutableState<String>? = null,
        Iv: MutableState<String>? = null,
        Iw: MutableState<String>? = null,
        P1: MutableState<String>? = null,
        cos: MutableState<String>? = null,
        F: MutableState<String>? = null
        ) {
        appendMessageToLog("${("Initialization")} PM130...", LogType.MESSAGE)
        pm130.checkResponsibility()

        if (isTestRunning.value) sleep(1000)
        if (!pm130.isResponding) {
            appendMessageToLog("PM130 ${("does not respond")}", LogType.ERROR)
            isTestRunning.value = false
        }

        if (Uuv != null && Uvw != null && Uwu != null) {
            CM.startPoll(CM.DeviceID.PAV41.name, PM130Model.U_AB_REGISTER) { value ->
                Uuv.value = (value.toDouble() * ktrVoltage).af()
                if (value.toDouble() * ktrVoltage > testObject.u_linear.toInt() * 1.1) {
                    appendMessageToLog(("Overvoltage"), LogType.ERROR)
                    isTestRunning.value = false
                }
                if (!pm130.isResponding && isTestRunning.value) {
                    appendMessageToLog("PM130 ${("does not respond")}", LogType.ERROR)
                    isTestRunning.value = false
                }
            }
            CM.startPoll(CM.DeviceID.PAV41.name, PM130Model.U_BC_REGISTER) { value ->
                Uvw.value = (value.toDouble() * ktrVoltage).af()
                if (value.toDouble() * ktrVoltage > testObject.u_linear.toInt() * 1.1) {
                    appendMessageToLog(("Overvoltage"), LogType.ERROR)
                    isTestRunning.value = false
                }
            }
            CM.startPoll(CM.DeviceID.PAV41.name, PM130Model.U_CA_REGISTER) { value ->
                Uwu.value = (value.toDouble() * ktrVoltage).af()
                voltAverage =
                    (Uuv.value.toDoubleOrDefault(0.0)
                            + Uvw.value.toDoubleOrDefault(0.0)
                            + Uwu.value.toDoubleOrDefault(0.0)) / 3 * ktrVoltage
                if (value.toDouble() * ktrVoltage > testObject.u_linear.toInt() * 1.1) {
                    appendMessageToLog(("Overvoltage"), LogType.ERROR)
                    isTestRunning.value = false
                }
            }
        }
        if (Iu != null && Iv != null && Iw != null) {
            CM.startPoll(CM.DeviceID.PAV41.name, PM130Model.I_A_REGISTER) {
                Iu.value = (it.toDouble() * ktrAmperage).af()
            }
            CM.startPoll(CM.DeviceID.PAV41.name, PM130Model.I_B_REGISTER) {
                Iv.value = (it.toDouble() * ktrAmperage).af()
            }
            CM.startPoll(CM.DeviceID.PAV41.name, PM130Model.I_C_REGISTER) {
                Iw.value = (it.toDouble() * ktrAmperage).af()
            }
        }
        if (P1 != null) {
            CM.startPoll(CM.DeviceID.PAV41.name, PM130Model.P_REGISTER) {
                P1.value = abs(it.toDouble() * ktrAmperage * ktrVoltage).af()
            }
        }
        if (cos != null) {
            CM.startPoll(CM.DeviceID.PAV41.name, PM130Model.COS_REGISTER) {
                cos.value = abs(it.toDouble()).af()
            }
        }
        if (F != null) {
            CM.startPoll(CM.DeviceID.PAV41.name, PM130Model.F_REGISTER) { F.value = it.af() }
        }
    }

    fun chooseCurrentStage(
        iu: MutableState<String>,
        iv: MutableState<String>,
        iw: MutableState<String>
    ) {
        appendMessageToLog("Подбор токовой ступени", LogType.MESSAGE)
        var Iu = iu.value.toDoubleOrDefault(100.0)
        var Iv = iv.value.toDoubleOrDefault(100.0)
        var Iw = iw.value.toDoubleOrDefault(100.0)

        appendMessageToLog("Проверка <80А: $Iu А | $Iv А | $Iw А", LogType.MESSAGE)
        if (Iu < 80.0 && Iv < 80.0 && Iw < 80.0) {
            pr102.i80(true)
            pr102.iMax250(false)
            ktrAmperage = 80.0 / 5.0
        }
        if (isTestRunning.value) {
            var timer = 5.0
            while (isTestRunning.value && timer > 0) {
                sleep(100)
                timer -= 0.1
            }
        }
        Iu = iu.value.toDoubleOrDefault(100.0)
        Iv = iv.value.toDoubleOrDefault(100.0)
        Iw = iw.value.toDoubleOrDefault(100.0)
        appendMessageToLog("Проверка <20А: $Iu А | $Iv А | $Iw А", LogType.MESSAGE)
        if (Iu < 20.0 && Iv < 20.0 && Iw < 20.0) {
            pr102.i20(true)
            pr102.i80(false)
            ktrAmperage = 20.0 / 5.0
        }
        if (isTestRunning.value) {
            var timer = 5.0
            while (isTestRunning.value && timer > 0) {
                Thread.sleep(100)
                timer -= 0.1
            }
        }
        Iu = iu.value.toDoubleOrDefault(100.0)
        Iv = iv.value.toDoubleOrDefault(100.0)
        Iw = iw.value.toDoubleOrDefault(100.0)
        appendMessageToLog("Проверка <5А: $Iu А | $Iv А | $Iw А", LogType.MESSAGE)
        if (Iu < 5.0 && Iv < 5.0 && Iw < 5.0) {
            pr102.iMin(true)
            pr102.i20(false)
            ktrAmperage = 5.0 / 5.0
        }

    }
}