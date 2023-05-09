package io.github.mmolosay.musicmind.presentation.ui.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

@Composable
fun MusicMindTheme(
    useDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) =
    CompositionLocalProvider(
        LocalMusicMindColors provides musicMindColors(useDark),
    ) {
        MaterialTheme(
            colorScheme = materialColors(useDark),
            content = content,
        )
    }

object MusicMindTheme {

    data class ColorScheme(
        val keyboardNaturals: Color,
        val keyboardNaturalsIndication: Color,
        val keyboardAccidentals: Color,
        val keyboardAccidentalsIndication: Color,
    )

    val colors: ColorScheme
        @Composable get() = LocalMusicMindColors.current
}