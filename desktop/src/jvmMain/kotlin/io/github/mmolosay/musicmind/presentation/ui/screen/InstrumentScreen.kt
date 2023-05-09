package io.github.mmolosay.musicmind.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.mmolosay.musicmind.presentation.ui.Keyboard
import io.github.mmolosay.musicmind.presentation.ui.components.Screen

@Composable
fun InstrumentScreen() =
    Screen {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Keyboard(
                modifier = Modifier
                    .height(400.dp),
                octaves = 2,
                onNaturalKeyClick = { println("White key!") },
                onAccidentalKeyClick = { println("Black key!") },
            )
        }
    }