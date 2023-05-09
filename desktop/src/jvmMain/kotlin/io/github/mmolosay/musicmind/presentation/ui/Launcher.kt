package io.github.mmolosay.musicmind.presentation.ui

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun launchApplication() {
    application {
        val state = rememberWindowState(
            position = WindowPosition(Alignment.Center),
            size = DpSize(width = 1200.dp, height = 900.dp),
        )
        Window(
            state = state,
            title = "MusicMind",
            onCloseRequest = ::exitApplication,
        ) {
            MusicMindApplication()
        }
    }
}