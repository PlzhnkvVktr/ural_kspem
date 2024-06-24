package ru.avem.modules.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class TestItem(
    val name: MutableState<String> = mutableStateOf(""),

    //mgr
    var specifiedMgrU: MutableState<String> = mutableStateOf(""),
    val mgrU: MutableState<String> = mutableStateOf(""),
    val r15: MutableState<String> = mutableStateOf(""),
    val r60: MutableState<String> = mutableStateOf(""),
    val kABS: MutableState<String> = mutableStateOf(""),
    val timeMGR: MutableState<String> = mutableStateOf(""),

    //viu
    var specifiedU_viu: MutableState<String> = mutableStateOf(""),
    var specifiedI_viu: MutableState<String> = mutableStateOf(""),
    var specifiedT_viu: MutableState<String> = mutableStateOf(""),
    var u_viu: MutableState<String> = mutableStateOf(""),
    var i_viu: MutableState<String> = mutableStateOf(""),
    var t_viu: MutableState<String> = mutableStateOf(""),
    var res_viu: MutableState<String> = mutableStateOf(""),

    //ikas
    var r_uv_ikas: MutableState<String> = mutableStateOf(""),
    var r_vw_ikas: MutableState<String> = mutableStateOf(""),
    var r_wu_ikas: MutableState<String> = mutableStateOf(""),

    //hh
    var u_uv_hh: MutableState<String> = mutableStateOf(""),
    var u_vw_hh: MutableState<String> = mutableStateOf(""),
    var u_wu_hh: MutableState<String> = mutableStateOf(""),
    var i_u_hh: MutableState<String> = mutableStateOf(""),
    var i_v_hh: MutableState<String> = mutableStateOf(""),
    var i_w_hh: MutableState<String> = mutableStateOf(""),

    //mv
    var u_uv_mv: MutableState<String> = mutableStateOf(""),
    var u_vw_mv: MutableState<String> = mutableStateOf(""),
    var u_wu_mv: MutableState<String> = mutableStateOf(""),
    var before_u_uv_mv: MutableState<String> = mutableStateOf(""),
    var before_u_vw_mv: MutableState<String> = mutableStateOf(""),
    var before_u_wu_mv: MutableState<String> = mutableStateOf(""),
    var after_u_uv_mv: MutableState<String> = mutableStateOf(""),
    var after_u_vw_mv: MutableState<String> = mutableStateOf(""),
    var after_u_wu_mv: MutableState<String> = mutableStateOf(""),

)



