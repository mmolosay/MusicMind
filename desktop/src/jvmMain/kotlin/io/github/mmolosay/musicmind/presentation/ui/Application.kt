package io.github.mmolosay.musicmind.presentation.ui

import androidx.compose.runtime.Composable
import io.github.mmolosay.musicmind.presentation.ui.design.MusicMindTheme
import io.github.mmolosay.musicmind.presentation.ui.screen.InstrumentScreen

@Composable
fun MusicMindApplication() =
    MusicMindTheme {
        InstrumentScreen()
    }
