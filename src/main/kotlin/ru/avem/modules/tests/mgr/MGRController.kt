package ru.avem.modules.tests.mgr

import ru.avem.common.ProtocolBuilder
import ru.avem.common.af
import ru.avem.db.TestItem
import ru.avem.modules.common.logger.LogType
import ru.avem.modules.devices.CM
import ru.avem.modules.devices.owen.pr.PR
import ru.avem.modules.devices.owen.trm202.TRM202Model
import ru.avem.modules.tests.CustomController
import ru.avem.modules.tests.CustomController.appendMessageToLog
import ru.avem.modules.tests.CustomController.initButtonPost
import ru.avem.modules.tests.CustomController.initPR
import ru.avem.modules.tests.CustomController.isTestRunning
import ru.avem.modules.tests.CustomController.pr102
import ru.avem.modules.tests.CustomController.testObject
import ru.avem.utils.getCurrentDate
import ru.avem.utils.getCurrentTime
import ru.avem.viewmodels.TestScreenViewModel
import java.lang.Thread.sleep
import kotlin.concurrent.thread

fun start(viewModel: TestScreenViewModel, currentTestItem: TestItem) {
    viewModel.clearFields()

    thread {
        viewModel.specifiedMgrU.value = testObject.specifiedMgrU
        isTestRunning.value = true
        viewModel.isDialog.value = true
        while (viewModel.isDialog.value) {
            sleep(200)
        }
        if (isTestRunning.value) initPR()
        var timer = 300
        while (viewModel.isDialog.value && timer > 0) {
            sleep(100)
            timer -= 1
        }
        if (viewModel.isDialog.value) isTestRunning.value = false


        isTestRunning.value = false
    }
}


fun addReport(mgrVM: MGRViewModel) {
    with(ProtocolBuilder) {
        U = mgrVM.U.value
        R15 = mgrVM.R15.value
        R60 = mgrVM.R60.value
        kABS = mgrVM.kABS.value
        mgrT = mgrVM.tempAmb.value
        operator = CustomController.currentOperator.value
        date = getCurrentDate()
        time = getCurrentTime()
        mgrResult = mgrVM.result.value
    }
}