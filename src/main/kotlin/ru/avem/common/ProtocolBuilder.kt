package ru.avem.common

import ru.avem.db.DBManager
import ru.avem.db.TestProtocol
import ru.avem.db.TestProtocols


object ProtocolBuilder {

    var id = ""
    var name =  ""
    var type =  ""
    var operator = ""
    var time = ""
    var date = ""

    var specifiedP = ""
    var specifiedU = ""
    var specifiedI = ""
    var specifiedRPM = ""
    var specifiedIdleTime = ""
    var specifiedRunningTime = ""
    var specifiedMgrU = ""
    var specifiedViuU = ""
    var scheme = ""

    //MGR
    var mgrTestName = ""
    var U = ""
    var R15 = ""
    var R60 = ""
    var kABS = ""
    var mgrT = ""
    var mgrResult = ""

    //VIU
    var viuTestName = ""
    var viuU = ""
    var viuI = ""
    var viuTime = ""
    var viuResult = ""

    //IKAS
    var ikasTestName = ""
    var Ruv1 = ""
    var Rvw1 = ""
    var Rwu1 = ""
    var Ruv2 = ""
    var Rvw2 = ""
    var Rwu2 = ""
    var deviationIkas = ""
    var ikasResult = ""

    //MV
    var mvTestName = ""
    var Uuv = ""
    var Uvw = ""
    var Uwu = ""
    var beforeIu = ""
    var beforeIv = ""
    var beforeIw = ""
    var afterIu = ""
    var afterIv = ""
    var afterIw = ""
    var beforeUuv = ""
    var beforeUvw = ""
    var beforeUwu = ""
    var afterUuv = ""
    var afterUvw = ""
    var afterUwu = ""
    var mvCalcA = ""
    var mvCalcB = ""
    var mvCalcC = ""
    var mvResult = ""

    //TR//
    var trTestName = ""
    var trUAB = ""
    var trUBC = ""
    var trUCA = ""
    var trUAB1 = ""
    var trUBC1 = ""
    var trUCA1 = ""
    var trUAB2 = ""
    var trUBC2 = ""
    var trUCA2 = ""
    var trCalcUAB = ""
    var trCalcUBC = ""
    var trCalcUCA = ""
    var trUOV = ""
    var trIA = ""
    var trIB = ""
    var trIC = ""
    var trIOV = ""
    var trTempTI = ""
    var trTempAmb = ""
    var trSpeed = ""
    var trVibro1 = ""
    var trVibro2 = ""
    var trP1 = ""
    var trCos = ""
    var trResult = ""

    //HH//
    var hhTestName = ""
    var hhUAB = ""
    var hhUBC = ""
    var hhUCA = ""
    var hhUOV = ""
    var hhIA = ""
    var hhIB = ""
    var hhIC = ""
    var hhIOV = ""
    var hhTempTI = ""
    var hhTempAmb = ""
    var hhSpeed = ""
    var hhVibro1 = ""
    var hhVibro2 = ""
    var hhP1 = ""
    var hhCos = ""
    var hhTime = ""
    var hhResult = ""

    //idle
    var idleTestName = ""
    var idleUAB = ""
    var idleUBC = ""
    var idleUCA = ""
    var idleUOV = ""
    var idleIA = ""
    var idleIB = ""
    var idleIC = ""
    var idleVibro1 = ""
    var idleVibro2 = ""
    var idleTime = ""
    var idleIOV = ""
    var idleSpeed = ""
    var idleF = ""
    var idleP1 = ""
    var idleCos = ""
    var idleResult = ""
    var idleTempTI = ""
    var idleTempAmb = ""

    //KZ//
    var kzTestName = ""
    var kzUAB = ""
    var kzUBC = ""
    var kzUCA = ""
    var kzUOV = ""
    var kzIA = ""
    var kzIB = ""
    var kzIC = ""
    var kzIOV = ""
    var kzTempTI = ""
    var kzTempAmb = ""
    var kzSpeed = ""
    var kzVibro1 = ""
    var kzVibro2 = ""
    var kzP1 = ""
    var kzCos = ""
    var kzResult = ""

    fun clear() {
        id = ""
        name =  ""
        type =  ""
        operator = ""
        time = ""
        date = ""

        specifiedP = ""
        specifiedU = ""
        specifiedI = ""
        specifiedRPM = ""
        specifiedIdleTime = ""
        specifiedRunningTime = ""
        specifiedMgrU = ""
        specifiedViuU = ""
        scheme = ""

        //MGR
        mgrTestName = ""
        U = ""
        R15 = ""
        R60 = ""
        kABS = ""
        mgrT = ""
        mgrResult = ""

        //IKAS
        ikasTestName = ""
        Ruv1 = ""
        Rvw1 = ""
        Rwu1 = ""
        Ruv2 = ""
        Rvw2 = ""
        Rwu2 = ""
        deviationIkas = ""
        ikasResult = ""

        //VIU
        viuTestName = ""
        viuU = ""
        viuI = ""
        viuTime = ""
        viuResult = ""

        //MV
        mvTestName = ""
        Uuv = ""
        Uvw = ""
        Uwu = ""
        beforeIu = ""
        beforeIv = ""
        beforeIw = ""
        afterIu = ""
        afterIv = ""
        afterIw = ""
        beforeUuv = ""
        beforeUvw = ""
        beforeUwu = ""
        afterUuv = ""
        afterUvw = ""
        afterUwu = ""
        mvCalcA = ""
        mvCalcB = ""
        mvCalcC = ""
        mvResult = ""

        //TR
        trTestName = ""
        trUAB = ""
        trUBC = ""
        trUCA = ""
        trUAB1 = ""
        trUBC1 = ""
        trUCA1 = ""
        trUAB2 = ""
        trUBC2 = ""
        trUCA2 = ""
        trCalcUAB = ""
        trCalcUBC = ""
        trCalcUCA = ""
        trUOV = ""
        trIA = ""
        trIB = ""
        trIC = ""
        trIOV = ""
        trTempTI = ""
        trTempAmb = ""
        trSpeed = ""
        trVibro1 = ""
        trVibro2 = ""
        trP1 = ""
        trCos = ""
        trResult = ""

        //hh
        hhTestName = ""
        hhUAB = ""
        hhUBC = ""
        hhUCA = ""
        hhUOV = ""
        hhIA = ""
        hhIB = ""
        hhIC = ""
        hhIOV = ""
        hhTempTI = ""
        hhTempAmb = ""
        hhSpeed = ""
        hhVibro1 = ""
        hhVibro2 = ""
        hhP1 = ""
        hhCos = ""
        hhTime = ""
        hhResult = ""

        //idle
        idleTestName = ""
        idleUAB = ""
        idleUBC = ""
        idleUCA = ""
        idleUOV = ""
        idleIA = ""
        idleIB = ""
        idleIC = ""
        idleVibro1 = ""
        idleVibro2 = ""
        idleTime = ""
        idleIOV = ""
        idleSpeed = ""
        idleF = ""
        idleP1 = ""
        idleCos = ""
        idleResult = ""
        idleTempTI = ""
        idleTempAmb = ""

        //KZ
        kzTestName = ""
        kzUAB = ""
        kzUBC = ""
        kzUCA = ""
        kzUOV = ""
        kzIA = ""
        kzIB = ""
        kzIC = ""
        kzIOV = ""
        kzTempTI = ""
        kzTempAmb = ""
        kzSpeed = ""
        kzVibro1 = ""
        kzVibro2 = ""
        kzP1 = ""
        kzCos = ""
        kzResult = ""
    }

    fun fillProtocol (protocol: TestProtocol) {

        val t = DBManager.getProtocolById(protocol)

        id = t.id.toString()
        name =  t.name
        type =  t.type.toString()
        operator = t.operator.toString()
        time = t.time.toString()
        date = t.date.toString()

        specifiedP = t.specifiedP.toString()
        specifiedU = t.specifiedU.toString()
        specifiedI = t.specifiedI.toString()
        specifiedRPM = t.specifiedRPM.toString()
        specifiedIdleTime = t.specifiedIdleTime.toString()
        specifiedRunningTime = t.specifiedRunningTime.toString()
        specifiedMgrU = t.specifiedMgrU.toString()
        specifiedViuU = t.specifiedViuU.toString()
        scheme = t.scheme.toString()

        //MGR
        U = t.mgrU.toString()
        R15 = t.mgrR15.toString()
        R60 = t.mgrR60.toString()
        kABS = t.mgrkABS.toString()
        mgrT = t.mgrT.toString()
        mgrResult = t.mgrResult.toString()

        //VIU
        viuU = t.viuU.toString()
        viuI = t.viuI.toString()
        viuTime = t.viuTime.toString()
        viuResult = t.viuResult.toString()

         //IKAS
        Ruv1 = t.Ruv1.toString()
        Rvw1 = t.Rvw1.toString()
        Rwu1 = t.Rwu1.toString()
        Ruv2 = t.Ruv2.toString()
        Rvw2 = t.Rvw2.toString()
        Rwu2 = t.Rwu2.toString()
        deviationIkas = t.deviationIkas.toString()
        ikasResult = t.ikasResult.toString()

        //MV
        Uuv = t.Uuv.toString()
        Uvw = t.Uvw.toString()
        Uwu = t.Uwu.toString()
        beforeIu = t.beforeIu.toString()
        beforeIv = t.beforeIv.toString()
        beforeIw = t.beforeIw.toString()
        afterIu = t.afterIu.toString()
        afterIv = t.afterIv.toString()
        afterIw = t.afterIw.toString()
        beforeUuv = t.beforeUuv.toString()
        beforeUvw = t.beforeUvw.toString()
        beforeUwu = t.beforeUwu.toString()
        afterUuv = t.afterUuv.toString()
        afterUvw = t.afterUvw.toString()
        afterUwu = t.afterUwu.toString()
        mvCalcA = t.mvCalcA.toString()
        mvCalcB = t.mvCalcB.toString()
        mvCalcC = t.mvCalcC.toString()
        mvResult = t.mvResult.toString()

        //TR
        trUAB = t.trUAB.toString()
        trUBC = t.trUBC.toString()
        trUCA = t.trUCA.toString()
        trUAB1 = t.trUAB1.toString()
        trUBC1 = t.trUBC1.toString()
        trUCA1 = t.trUCA1.toString()
        trUAB2 = t.trUAB2.toString()
        trUBC2 = t.trUBC2.toString()
        trUCA2 = t.trUCA2.toString()
        trCalcUAB = t.trCalcUAB.toString()
        trCalcUBC = t.trCalcUBC.toString()
        trCalcUCA = t.trCalcUCA.toString()
        trUOV = t.trUOV.toString()
        trIA = t.trIA.toString()
        trIB = t.trIB.toString()
        trIC = t.trIC.toString()
        trIOV = t.trIOV.toString()
        trTempTI = t.trTempTI.toString()
        trTempAmb = t.trTempAmb.toString()
        trSpeed = t.trSpeed.toString()
        trVibro1 = t.trVibro1.toString()
        trVibro2 = t.trVibro2.toString()
        trP1 = t.trP1.toString()
        trCos = t.trCos.toString()
        trResult = t.trResult.toString()

        //KZ
        kzUAB = t.kzUAB.toString()
        kzUBC = t.kzUBC.toString()
        kzUCA = t.kzUCA.toString()
        kzUOV = t.kzUOV.toString()
        kzIA = t.kzIA.toString()
        kzIB = t.kzIB.toString()
        kzIC = t.kzIC.toString()
        kzIOV = t.kzIOV.toString()
        kzTempTI = t.kzTempTI.toString()
        kzTempAmb = t.kzTempAmb.toString()
        kzSpeed = t.kzSpeed.toString()
        kzVibro1 = t.kzVibro1.toString()
        kzVibro2 = t.kzVibro2.toString()
        kzP1 = t.kzP1.toString()
        kzCos = t.kzCos.toString()
        kzResult = t.kzResult.toString()

        //HH
        hhUAB = t.hhUAB.toString()
        hhUBC = t.hhUBC.toString()
        hhUCA = t.hhUCA.toString()
        hhUOV = t.hhUOV.toString()
        hhIA = t.hhIA.toString()
        hhIB = t.hhIB.toString()
        hhIC = t.hhIC.toString()
        hhIOV = t.hhIOV.toString()
        hhTempTI = t.hhTempTI.toString()
        hhTempAmb = t.hhTempAmb.toString()
        hhSpeed = t.hhSpeed.toString()
        hhVibro1 = t.hhVibro1.toString()
        hhVibro2 = t.hhVibro2.toString()
        hhP1 = t.hhP1.toString()
        hhCos = t.hhCos.toString()
        hhTime = t.hhTime.toString()
        hhResult = t.hhResult.toString()

        //idle
        idleUAB = t.idleUAB.toString()
        idleUBC = t.idleUBC.toString()
        idleUCA = t.idleUCA.toString()
        idleUOV = t.idleUOV.toString()
        idleIA = t.idleIA.toString()
        idleIB = t.idleIB.toString()
        idleIC = t.idleIC.toString()
        idleVibro1 = t.idleVibro1.toString()
        idleVibro2 = t.idleVibro2.toString()
        idleTime = t.idleTime.toString()
        idleIOV = t.idleIOV.toString()
        idleSpeed = t.idleSpeed.toString()
        idleF = t.idleF.toString()
        idleP1 = t.idleP1.toString()
        idleCos = t.idleCos.toString()
        idleResult = t.idleResult.toString()
        idleTempTI = t.idleTempTI.toString()
        idleTempAmb = t.idleTempAmb.toString()

    }

}
