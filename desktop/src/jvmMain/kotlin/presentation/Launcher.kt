package presentation

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import presentation.ui.MTheoryApplication

fun launchApplication() {
    application {
        Window(
            title = "MusicMind",
            onCloseRequest = ::exitApplication,
        ) {
            MTheoryApplication()
        }
    }
}