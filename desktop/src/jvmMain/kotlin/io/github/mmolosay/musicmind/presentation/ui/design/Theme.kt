package io.github.mmolosay.musicmind.presentation.ui.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
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
            colors = if (useDark) darkColors() else lightColors(),
            content = content,
        )
    }

object MusicMindTheme {

    data class Colors(
        val keyboardNaturals: Color,
        val keyboardAccidentals: Color,
    )

    val colors: Colors
        @Composable get() = LocalMusicMindColors.current
}