package io.github.mmolosay.musicmind.presentation.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.mmolosay.musicmind.presentation.ui.design.MusicMindTheme
import kotlin.math.roundToInt

// region Previews

@Preview
@Composable
private fun Keyboard_Preview() {
    Keyboard(
        modifier = Modifier.size(400.dp, 380.dp)
    )
}

// endregion

@Composable
fun Keyboard(
    modifier: Modifier = Modifier,
    octaves: Int = 2,
    naturalsSpacing: Dp = 2.dp,
) {
    require(octaves > 0) { "Octaves number must be posititve" }
    Row(modifier = modifier) {
        repeat(octaves) {
            KeyboardOctave(
                naturalsSpacing = naturalsSpacing,
            )
        }
    }
}

@Composable
private fun KeyboardOctave(
    naturalsSpacing: Dp,
) {
    val density = LocalDensity.current
    val measurePolicy = remember {
        keyboardOctaveMeasurePolicy(
            naturalsSpacing = with(density) { naturalsSpacing.roundToPx() },
        )
    }
    Layout(
        measurePolicy = measurePolicy,
        content = {
            KeyboardNaturalKey()
            KeyboardAccidentalKey()
            KeyboardNaturalKey()
            KeyboardAccidentalKey()
            KeyboardNaturalKey()
            KeyboardNaturalKey()
            KeyboardAccidentalKey()
            KeyboardNaturalKey()
            KeyboardAccidentalKey()
            KeyboardNaturalKey()
            KeyboardAccidentalKey()
            KeyboardNaturalKey()
        }
    )
}

private fun keyboardOctaveMeasurePolicy(
    naturalsSpacing: Int,
): MeasurePolicy =
    MeasurePolicy { measurables, constraints ->
        val naturals = 7
        val totalNaturalsSpacing = (naturals - 1) * naturalsSpacing
        val naturalWidth = ((constraints.maxWidth - totalNaturalsSpacing) / naturals.toFloat()).roundToInt()
        val naturalHeight = naturalWidth * 4
        val accidentalWidth = (naturalWidth * (1f / 2)).roundToInt()
        val accidentalHeight = (naturalHeight * (2f / 3)).roundToInt()
        val placeables = measurables
            .zip(KeyboardOctaveKeys)
            .map { (measurable, isNatural) ->
                if (isNatural)
                    measurable.measure(
                        constraints.copy(
                            minWidth = naturalWidth,
                            maxWidth = naturalWidth,
                            minHeight = naturalHeight,
                            maxHeight = naturalHeight,
                        )
                    )
                else
                    measurable.measure(
                        constraints.copy(
                            minWidth = accidentalWidth,
                            maxWidth = accidentalWidth,
                            minHeight = accidentalHeight,
                            maxHeight = accidentalHeight,
                        )
                    )
            }
            .zip(KeyboardOctaveKeys)
        layout(constraints.maxWidth, naturalHeight) {
            var nX = 0
            val aOffset = (naturalsSpacing + accidentalWidth) / 2
            for ((item, isNatural) in placeables) {
                if (isNatural) {
                    item.placeRelative(nX, 0, 0f)
                    nX += item.width + naturalsSpacing
                } else {
                    item.placeRelative(nX - aOffset, 0, 1f)
                }
            }
        }
    }

@Composable
private fun KeyboardNaturalKey(
    color: Color = MusicMindTheme.colors.keyboardNaturals,
    cornerRadius: Dp = 5.dp,
) {
    val density = LocalDensity.current
    val radius = CornerRadius(x = with(density) { cornerRadius.toPx() })
    Canvas(
        modifier = Modifier.fillMaxSize(),
    ) {

        drawRoundRect(
            color = color,
            cornerRadius = radius,
        )
    }
}

@Composable
private fun KeyboardAccidentalKey(
    color: Color = MusicMindTheme.colors.keyboardAccidentals,
    cornerRadius: Dp = 5.dp,
) {
    val density = LocalDensity.current
    val radius = CornerRadius(x = with(density) { cornerRadius.toPx() })
    Canvas(
        modifier = Modifier.fillMaxSize().clickable { println("clicked") },
    ) {
        drawRoundRect(
            color = color,
            cornerRadius = radius,
        )
    }
}

private val KeyboardOctaveKeys by lazy {
    listOf(true, false, true, false, true, true, false, true, false, true, false, true)
}
