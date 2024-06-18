package ru.avem.modules.models

import ru.avem.db.TestItem

data class TestObject(
    val name: String,
    val scheme: Boolean,
    val power: String,
    val u_linear: String,
    val i: String,
    val i_viu: String,
    val i_mz: String,
    val u_viu: String,
    val u_mgr: String,
    val t_viu: String,
    val t_hh: String,
    val t_mv: String,
    val r_max: String,
    val r_min: String,
    val r20_max: String,
    val r20_min: String,
    val t: String
)



