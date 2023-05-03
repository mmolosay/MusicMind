package presentation

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import presentation.ui.MTheoryApplication

fun launchApplication() {
    application {
        val state = rememberWindowState(position = WindowPosition(Alignment.Center))
        Window(
            state = state,
            title = "MusicMind",
            onCloseRequest = ::exitApplication,
        ) {
            MTheoryApplication()
        }
    }
}