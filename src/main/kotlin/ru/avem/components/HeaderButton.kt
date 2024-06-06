package ru.avem.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

@Composable
fun HeaderButton (text: String, screen: Screen) {
    val navigator = LocalNavigator.currentOrThrow

    OutlinedButton(
        onClick = {
            navigator.replace(screen)
        },
        modifier = Modifier.width(170.dp)
        ) {
        Text(text, style = MaterialTheme.typography.h6)
    }
}