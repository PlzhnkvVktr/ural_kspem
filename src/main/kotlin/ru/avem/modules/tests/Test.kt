package ru.avem.modules.tests

import cafe.adriel.voyager.core.screen.Screen
import ru.avem.viewmodels.MainScreenViewModel

//interface Test : Screen {
//    val testName: String
//}

abstract class Test (

): Screen, MainScreenViewModel() {
    open val testName: String = ""
}
