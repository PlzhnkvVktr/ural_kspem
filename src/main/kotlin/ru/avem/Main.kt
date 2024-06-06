package ru.avem

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import ru.avem.db.DBManager
import ru.avem.screens.MainScreen

fun main() = application {
    DBManager
    Window(
        onCloseRequest = ::exitApplication,
        title = "КСПЭМ",
        icon = painterResource("ru/logo/logo.ico")
    ) {
        Navigator(screen = MainScreen())
    }
}
