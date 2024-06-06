package ru.avem.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.avem.screens.AuthScreen
import ru.avem.screens.MainScreen

@Composable
fun Header(
//    isAdmin: Boolean = AppConfig.params.isAdmin
) {
    Row (
        Modifier.fillMaxWidth()
    ) {
        HeaderButton("Испытания", MainScreen())
//        HeaderButton(word("Test item"), EditorOIScreen(MainScreenViewModel()))
//        HeaderButton(word("Protocols"), ProtocolScreen())
//        if (isAdmin) {
//            HeaderButton(word("Users"), SettingsScreen())
//        }
        HeaderButton("Выйти", AuthScreen())
    }
}
