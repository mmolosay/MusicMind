package io.github.mmolosay.musicmind.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.mmolosay.musicmind.presentation.ui.design.MusicMindTheme

@Composable
fun MusicMindApplication() =
    MusicMindTheme {
        KeyboardScreen()
    }

@Composable
private fun KeyboardScreen() =
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(
            modifier = Modifier.offset(x = 20.dp, y = 20.dp),
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