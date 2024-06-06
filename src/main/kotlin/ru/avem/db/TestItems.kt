package ru.avem.db

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object TestItems : IntIdTable() {
    var name = varchar("name", 50)
    val scheme = bool("scheme")
    val specifiedP = varchar("specifiedP", 25)
    var specifiedU = varchar("specifiedU", 25)
    val specifiedI = varchar("specifiedI", 25)
    val specifiedRPM = varchar("specifiedRPM", 25)
    val specifiedIdleTime = varchar("specifiedIdleTime", 25)
    var specifiedRunningTime = varchar("specifiedRunningTime", 25)
    val specifiedMgrU = varchar("specifiedMgrU", 25)
    val specifiedViuU = varchar("specifiedViuU", 25)
    val specifiedViuTime = varchar("specifiedViuTime", 25)
}

class TestItem(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TestItem>(TestItems)

    var name by TestItems.name

    var scheme by TestItems.scheme
    var specifiedP by TestItems.specifiedP
    var specifiedU by TestItems.specifiedU
    var specifiedI by TestItems.specifiedI
    var specifiedRPM by TestItems.specifiedRPM
    var specifiedIdleTime by TestItems.specifiedIdleTime
    var specifiedRunningTime by TestItems.specifiedRunningTime
    var specifiedMgrU by TestItems.specifiedMgrU
    var specifiedViuU by TestItems.specifiedViuU
    var specifiedViuTime by TestItems.specifiedViuTime

//    fun list() = mapOf(name to name, specifiedI to specifiedI, specifiedP to specifiedP, specifiedU to specifiedU, specifiedRPM to specifiedRPM)

//    override fun toString() = specifiedP


    override fun toString() = name

}