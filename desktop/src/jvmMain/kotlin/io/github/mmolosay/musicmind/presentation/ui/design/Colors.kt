package io.github.mmolosay.musicmind.presentation.ui.design

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalMusicMindColors = staticCompositionLocalOf { lightMusicMindColors() }

@Composable
fun materialColors(
    useDark: Boolean,
): ColorScheme =
    if (useDark) materialDarkColors()
    else materialLightColors()

private fun materialLightColors() =
    lightColorScheme(
    )

private fun materialDarkColors() =
    darkColorScheme(
    )

@Composable
fun musicMindColors(
    useDark: Boolean,
): MusicMindTheme.ColorScheme =
    if (useDark) darkMusicMindColors()
    else lightMusicMindColors()

private fun lightMusicMindColors(): MusicMindTheme.ColorScheme =
    MusicMindTheme.ColorScheme(
        keyboardNaturals = Color.White,
        keyboardNaturalsIndication = Color.DarkGray,
        keyboardAccidentals = Color.DarkGray,
        keyboardAccidentalsIndication = Color.LightGray,
    )

private fun darkMusicMindColors(): MusicMindTheme.ColorScheme =
    MusicMindTheme.ColorScheme(
        keyboardNaturals = Color.White,
        keyboardNaturalsIndication = Color.DarkGray,
        keyboardAccidentals = Color(0xff1e1e1e),
        keyboardAccidentalsIndication = Color(0xFFEEEEEE),
    )