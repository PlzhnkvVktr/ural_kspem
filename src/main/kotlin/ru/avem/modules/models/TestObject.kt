package ru.avem.modules.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import ru.avem.db.TestItem

data class TestItem(
    val name: MutableState<String> = mutableStateOf(""),
    var specifiedMgrU: MutableState<String> = mutableStateOf(""),
    val mgrU: MutableState<String> = mutableStateOf(""),
    val R15: MutableState<String> = mutableStateOf(""),
    val R60: MutableState<String> = mutableStateOf(""),
    val kABS: MutableState<String> = mutableStateOf(""),
    val timeMGR: MutableState<String> = mutableStateOf("")
)



