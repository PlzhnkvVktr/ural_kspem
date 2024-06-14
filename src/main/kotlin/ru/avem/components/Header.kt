package ru.avem.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.avem.common.repos.AppConfig
import ru.avem.modules.models.SelectedTestObject
import ru.avem.screens.*

@Composable
fun Header(
    isAdmin: Boolean = AppConfig.params.isAdmin
) {
    Row (
        Modifier.fillMaxWidth()
    ) {
        HeaderButton("Испытания", SelectionTestObjectScreen())
        HeaderButton("Объкт испытания", EditorOIScreen())
        HeaderButton("Протоколы", ProtocolScreen())
        if (isAdmin) {
            HeaderButton("Пользователи", SettingsScreen())
        }
        HeaderButton("Выйти", AuthScreen())
    }
}
