package io.github.mmolosay.musicmind.presentation.ui.design

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalMusicMindColors = staticCompositionLocalOf { lightMusicMindColors() }

@Composable
fun musicMindColors(
    useDark: Boolean,
): MusicMindTheme.Colors =
    if (useDark) darkMusicMindColors()
    else lightMusicMindColors()

private fun lightMusicMindColors(): MusicMindTheme.Colors =
    MusicMindTheme.Colors(
        keyboardNaturals = Color.White,
        keyboardAccidentals = Color.DarkGray,
    )

private fun darkMusicMindColors(): MusicMindTheme.Colors =
    MusicMindTheme.Colors(
        keyboardNaturals = Color.White,
        keyboardAccidentals = Color(0xFF1F1F1F),
    )