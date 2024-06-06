package ru.avem.db

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object TestProtocols : IntIdTable() {


    var name = varchar("name", 64)
    var type = varchar("type", 64).nullable()
    var operator = varchar("operator", 256).nullable()
    var time = varchar("time", 64).nullable()
    var date = varchar("date", 64).nullable()

    val specifiedP = varchar("specifiedP", 64).nullable()
    var specifiedU = varchar("specifiedU", 64).nullable()
    val specifiedI = varchar("specifiedI", 64).nullable()
    val specifiedRPM = varchar("specifiedRPM", 64).nullable()
    val specifiedIdleTime = varchar("specifiedIdleTime", 64).nullable()
    var specifiedRunningTime = varchar("specifiedRunningTime", 64).nullable()
    val specifiedMgrU = varchar("specifiedMgrU", 64).nullable()
    val specifiedViuU = varchar("specifiedViuU", 64).nullable()
    val scheme = bool("scheme").nullable()

    //MGR мегер
    val mgrU = varchar("U", 64).nullable()
    var mgrR15 = varchar("R15", 64).nullable()
    val mgrR60 = varchar("R60", 64).nullable()
    val mgrkABS = varchar("kABS", 64).nullable()
    var mgrT = varchar("mgrT", 64).nullable()
    var mgrResult = varchar("mgrResult", 64).nullable()

    //IKAS Измерение сопротивления обмоток постоянному току в практически холодном состоянии

    val ikasRuv1 = varchar("Ruv1", 64).nullable()
    val ikasRvw1 = varchar("Rvw1", 64).nullable()
    val ikasRwu1 = varchar("Rwu1", 64).nullable()
    val ikasRuv2 = varchar("Ruv2", 64).nullable()
    val ikasRvw2 = varchar("Rvw2", 64).nullable()
    val ikasRwu2 = varchar("Rwu2", 64).nullable()
    val deviationIkas = varchar("deviationIkas", 64).nullable()
    var ikasResult = varchar("ikasResult", 64).nullable()

    //VIU Испытание изоляции обмоток относительно корпуса и между фазами на электрическую прочность

    var viuU = varchar("viuU", 64).nullable()
    var viuI = varchar("viuI", 64).nullable()
    var viuTime = varchar("viuTime", 64).nullable()
    var viuResult = varchar("viuResult", 64).nullable()

    //MV Испытание междувитковой изоляции

    val Uuv = varchar("Uuv", 64).nullable()
    val Uvw = varchar("Uvw", 64).nullable()
    val Uwu = varchar("Uwu", 64).nullable()
    var beforeIu = varchar("beforeIu", 64).nullable()
    var beforeIv = varchar("beforeIv", 64).nullable()
    var beforeIw = varchar("beforeIw", 64).nullable()
    var afterIu = varchar("afterIu", 64).nullable()
    var afterIv = varchar("afterIv", 64).nullable()
    var afterIw = varchar("afterIw", 64).nullable()
    val beforeUuv = varchar("beforeUuv", 25).nullable()
    val beforeUvw = varchar("beforeUvw", 25).nullable()
    val beforeUwu = varchar("beforeUwu", 25).nullable()
    val afterUuv = varchar("afterUuv", 25).nullable()
    val afterUvw = varchar("afterUvw", 25).nullable()
    val afterUwu = varchar("afterUwu", 25).nullable()
    val mvResult = varchar("mvResult", 25).nullable()
    var mvCalcA = varchar("mvCalcA", 64).nullable()
    var mvCalcB = varchar("mvCalcB", 64).nullable()
    var mvCalcC = varchar("mvCalcC", 64).nullable()

    //HH Определение тока и потерь ХХ

    var hhUAB = varchar("hhUAB", 64).nullable()
    var hhUBC = varchar("hhUBC", 64).nullable()
    var hhUCA = varchar("hhUCA", 64).nullable()
    var hhUOV = varchar("hhUOV", 64).nullable()
    var hhIA = varchar("hhIA", 64).nullable()
    var hhIB = varchar("hhIB", 64).nullable()
    var hhIC = varchar("hhIC", 64).nullable()
    var hhIOV = varchar("hhIOV", 64).nullable()
    var hhTempTI = varchar("hhTempTI", 64).nullable()
    var hhTempAmb = varchar("hhTempAmb", 64).nullable()
    var hhSpeed = varchar("hhSpeed", 64).nullable()
    var hhVibro1 = varchar("hhVibro1", 64).nullable()
    var hhVibro2 = varchar("hhVibro2", 64).nullable()
    var hhP1 = varchar("hhP1", 64).nullable()
    var hhCos = varchar("hhCos", 64).nullable()
    var hhTime = varchar("hhTime", 64).nullable()
    var hhResult = varchar("hhResult", 64).nullable()

    // idle Обкатка электродвигателей на холостом ходу
    var idleUAB = varchar("nUAB", 64).nullable()
    var idleUBC = varchar("nUBC", 64).nullable()
    var idleUCA = varchar("nUCA", 64).nullable()
    var idleUOV = varchar("nUOV", 64).nullable()
    var idleIA = varchar("nIA", 64).nullable()
    var idleIB = varchar("nIB", 64).nullable()
    var idleIC = varchar("nIC", 64).nullable()
    var idleIOV = varchar("nIOV", 64).nullable()
    var idleVibro1 = varchar("nVibro1", 64).nullable()
    var idleVibro2 = varchar("nVibro2", 64).nullable()
    var idleTempTI = varchar("nTempTI", 64).nullable()
    var idleTime = varchar("nTime", 64).nullable()
    var idleTempAmb = varchar("nTempAmb", 64).nullable()
    var idleSpeed = varchar("nSpeed", 64).nullable()
    var idleF = varchar("nF", 64).nullable()
    var idleP1 = varchar("nP1", 64).nullable()
    var idleCos = varchar("nCos", 64).nullable()
    var idleResult = varchar("nResult", 64).nullable()

    //TR Определение коэффициента трансформации
    var trUAB = varchar("trUAB", 64).nullable()
    var trUBC = varchar("trUBC", 64).nullable()
    var trUCA = varchar("trUCA", 64).nullable()
    var trUAB1 = varchar("trUAB1", 64).nullable()
    var trUBC1 = varchar("trUBC1", 64).nullable()
    var trUCA1 = varchar("trUCA1", 64).nullable()
    var trUAB2 = varchar("trUAB2", 64).nullable()
    var trUBC2 = varchar("trUBC2", 64).nullable()
    var trUCA2 = varchar("trUCA2", 64).nullable()
    var trCalcUAB = varchar("trCalcUAB", 64).nullable()
    var trCalcUBC = varchar("trCalcUBC", 64).nullable()
    var trCalcUCA = varchar("trCalcUCA", 64).nullable()
    var trUOV = varchar("trUOV", 64).nullable()
    var trIA = varchar("trIA", 64).nullable()
    var trIB = varchar("trIB", 64).nullable()
    var trIC = varchar("trIC", 64).nullable()
    var trIOV = varchar("trIOV", 64).nullable()
    var trTempTI = varchar("trTempTI", 64).nullable()
    var trTempAmb = varchar("trTempAmb", 64).nullable()
    var trSpeed = varchar("trSpeed", 64).nullable()
    var trVibro1 = varchar("trVibro1", 64).nullable()
    var trVibro2 = varchar("trVibro2", 64).nullable()
    var trP1 = varchar("trP1", 64).nullable()
    var trCos = varchar("trCos", 64).nullable()
    var trResult = varchar("trResult", 64).nullable()

    //KZ Определение тока и потерь короткого замыкания
    var kzUAB = varchar("kzUAB", 64).nullable()
    var kzUBC = varchar("kzUBC", 64).nullable()
    var kzUCA = varchar("kzUCA", 64).nullable()
    var kzUOV = varchar("kzUOV", 64).nullable()
    var kzIA = varchar("kzIA", 64).nullable()
    var kzIB = varchar("kzIB", 64).nullable()
    var kzIC = varchar("kzIC", 64).nullable()
    var kzIOV = varchar("kzIOV", 64).nullable()
    var kzTempTI = varchar("kzTempTI", 64).nullable()
    var kzTempAmb = varchar("kzTempAmb", 64).nullable()
    var kzSpeed = varchar("kzSpeed", 64).nullable()
    var kzVibro1 = varchar("kzVibro1", 64).nullable()
    var kzVibro2 = varchar("kzVibro2", 64).nullable()
    var kzP1 = varchar("kzP1", 64).nullable()
    var kzCos = varchar("kzCos", 64).nullable()
    var kzResult = varchar("kzResult", 64).nullable()

}

class TestProtocol(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TestProtocol>(TestProtocols)

    var name by TestProtocols.name
    var type by TestProtocols.type
    var operator by TestProtocols.operator
    var time by TestProtocols.time
    var date by TestProtocols.date

    var specifiedP by TestProtocols.specifiedP
    var specifiedU by TestProtocols.specifiedU
    var specifiedI by TestProtocols.specifiedI
    var specifiedRPM by TestProtocols.specifiedRPM
    var specifiedIdleTime by TestProtocols.specifiedIdleTime
    var specifiedRunningTime by TestProtocols.specifiedRunningTime
    var specifiedMgrU by TestProtocols.specifiedMgrU
    var specifiedViuU by TestProtocols.specifiedViuU
    var scheme by TestProtocols.scheme

    //MGR
    var mgrU by TestProtocols.mgrU
    var mgrR15 by TestProtocols.mgrR15
    var mgrR60 by TestProtocols.mgrR60
    var mgrkABS by TestProtocols.mgrkABS
    var mgrT by TestProtocols.mgrT
    var mgrResult by TestProtocols.mgrResult


    //IKAS
    var Ruv1 by TestProtocols.ikasRuv1
    var Rvw1 by TestProtocols.ikasRvw1
    var Rwu1 by TestProtocols.ikasRwu1
    var Ruv2 by TestProtocols.ikasRuv2
    var Rvw2 by TestProtocols.ikasRvw2
    var Rwu2 by TestProtocols.ikasRwu2
    var deviationIkas by TestProtocols.deviationIkas
    var ikasResult by TestProtocols.ikasResult

    //VIU//
    var viuU by TestProtocols.viuU
    var viuI by TestProtocols.viuI
    var viuTime by TestProtocols.viuTime
    var viuResult by TestProtocols.viuResult

    //MV
    var Uuv by TestProtocols.Uuv
    var Uvw by TestProtocols.Uvw
    var Uwu by TestProtocols.Uwu
    var beforeIu by TestProtocols.beforeIu
    var beforeIv by TestProtocols.beforeIv
    var beforeIw by TestProtocols.beforeIw
    var afterIu by TestProtocols.afterIu
    var afterIv by TestProtocols.afterIv
    var afterIw by TestProtocols.afterIw
    var beforeUuv by TestProtocols.beforeUuv
    var beforeUvw by TestProtocols.beforeUvw
    var beforeUwu by TestProtocols.beforeUwu
    var afterUuv by TestProtocols.afterUuv
    var afterUvw by TestProtocols.afterUvw
    var afterUwu by TestProtocols.afterUwu
    var mvCalcA by TestProtocols.mvCalcA
    var mvCalcB by TestProtocols.mvCalcB
    var mvCalcC by TestProtocols.mvCalcC
    var mvResult by TestProtocols.mvResult


    //TR//
    var trUAB by TestProtocols.trUAB
    var trUBC by TestProtocols.trUBC
    var trUCA by TestProtocols.trUCA
    var trUAB1 by TestProtocols.trUAB1
    var trUBC1 by TestProtocols.trUBC1
    var trUCA1 by TestProtocols.trUCA1
    var trUAB2 by TestProtocols.trUAB2
    var trUBC2 by TestProtocols.trUBC2
    var trUCA2 by TestProtocols.trUCA2
    var trCalcUAB by TestProtocols.trCalcUAB
    var trCalcUBC by TestProtocols.trCalcUBC
    var trCalcUCA by TestProtocols.trCalcUCA
    var trUOV by TestProtocols.trUOV
    var trIA by TestProtocols.trIA
    var trIB by TestProtocols.trIB
    var trIC by TestProtocols.trIC
    var trIOV by TestProtocols.trIOV
    var trTempTI by TestProtocols.trTempTI
    var trTempAmb by TestProtocols.trTempAmb
    var trSpeed by TestProtocols.trSpeed
    var trVibro1 by TestProtocols.trVibro1
    var trVibro2 by TestProtocols.trVibro2
    var trP1 by TestProtocols.trP1
    var trCos by TestProtocols.trCos
    //    var trTime by ProtocolsTable.trTime
    var trResult by TestProtocols.trResult

    //KZ//
    var kzUAB by TestProtocols.kzUAB
    var kzUBC by TestProtocols.kzUBC
    var kzUCA by TestProtocols.kzUCA
    var kzUOV by TestProtocols.kzUOV
    var kzIA by TestProtocols.kzIA
    var kzIB by TestProtocols.kzIB
    var kzIC by TestProtocols.kzIC
    var kzIOV by TestProtocols.kzIOV
    var kzTempTI by TestProtocols.kzTempTI
    var kzTempAmb by TestProtocols.kzTempAmb
    var kzSpeed by TestProtocols.kzSpeed
    var kzVibro1 by TestProtocols.kzVibro1
    var kzVibro2 by TestProtocols.kzVibro2
    var kzP1 by TestProtocols.kzP1
    var kzCos by TestProtocols.kzCos
    //    var kzTime by ProtocolsTable.kzTime
    var kzResult by TestProtocols.kzResult

    //HH//
    var hhUAB by TestProtocols.hhUAB
    var hhUBC by TestProtocols.hhUBC
    var hhUCA by TestProtocols.hhUCA
    var hhUOV by TestProtocols.hhUOV
    var hhIA by TestProtocols.hhIA
    var hhIB by TestProtocols.hhIB
    var hhIC by TestProtocols.hhIC
    var hhIOV by TestProtocols.hhIOV
    var hhTempTI by TestProtocols.hhTempTI
    var hhTempAmb by TestProtocols.hhTempAmb
    var hhSpeed by TestProtocols.hhSpeed
    var hhVibro1 by TestProtocols.hhVibro1
    var hhVibro2 by TestProtocols.hhVibro2
    var hhP1 by TestProtocols.hhP1
    var hhCos by TestProtocols.hhCos
    var hhTime by TestProtocols.hhTime
    var hhResult by TestProtocols.hhResult


    //N//
    var idleUAB by TestProtocols.idleUAB
    var idleUBC by TestProtocols.idleUBC
    var idleUCA by TestProtocols.idleUCA
    var idleUOV by TestProtocols.idleUOV
    var idleIA by TestProtocols.idleIA
    var idleIB by TestProtocols.idleIB
    var idleIC by TestProtocols.idleIC
    var idleVibro1 by TestProtocols.idleVibro1
    var idleVibro2 by TestProtocols.idleVibro2
    var idleTime by TestProtocols.idleTime
    var idleIOV by TestProtocols.idleIOV
    var idleSpeed by TestProtocols.idleSpeed
    var idleF by TestProtocols.idleF
    var idleP1 by TestProtocols.idleP1
    var idleCos by TestProtocols.idleCos
    var idleResult by TestProtocols.idleResult
    var idleTempTI by TestProtocols.idleTempTI
    var idleTempAmb by TestProtocols.idleTempAmb

    override fun toString() = name

}