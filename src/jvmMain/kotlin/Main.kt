import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.MTheoryApplication

fun main() =
    application {
        Window(onCloseRequest = ::exitApplication) {
            MTheoryApplication()
        }
    }
