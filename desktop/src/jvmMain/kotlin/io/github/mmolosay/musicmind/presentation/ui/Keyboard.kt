package io.github.mmolosay.musicmind.presentation.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import io.github.mmolosay.musicmind.presentation.ui.design.MusicMindTheme

data class NaturalsSpacing(
    val naturalKeyWidthFraction: Float = 0.04f,
    val max: Dp = 6.dp,
)

@Composable
fun Keyboard(
    modifier: Modifier = Modifier,
    octaves: Int,
    onNaturalKeyClick: () -> Unit,
    onAccidentalKeyClick: () -> Unit,
    drawLabels: Boolean = true,
    naturalsColor: Color = MusicMindTheme.colors.keyboardNaturals,
    naturalsIndicationColor: Color = MusicMindTheme.colors.keyboardNaturalsIndication,
    accidentalsColor: Color = MusicMindTheme.colors.keyboardAccidentals,
    accidentalsIndicationColor: Color = MusicMindTheme.colors.keyboardAccidentalsIndication,
    spacing: NaturalsSpacing = NaturalsSpacing(),
) {
    BoxWithConstraints(
        modifier = modifier,
    ) {
        val naturals = 7
        val accidentals = 5
        val totalNaturals = naturals * octaves
        val totalSpacings = totalNaturals - 1
        val kb = calcKeyboardSize(octaves)
        var naturalsWidth = kb.width / (totalNaturals + (spacing.naturalKeyWidthFraction * totalSpacings))
        var space = naturalsWidth * spacing.naturalKeyWidthFraction
        if (space > spacing.max) { // recalculate naturals width
            space = spacing.max
            naturalsWidth = (kb.width - (space * totalSpacings)) / totalNaturals
        }
        val naturalsSize = DpSize(width = naturalsWidth, height = kb.height)
        val accidentalsSize = naturalsSize.run {
            copy(
                width = width * AccidentalsToNaturalsWidthRatio,
                height = height * AccidentalsToNaturalsHeightRatio,
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(space),
        ) {
            repeat(octaves) {
                Box {
                    // Naturals
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(space),
                    ) {
                        val indication = rememberRipple(color = naturalsIndicationColor)
                        repeat(naturals) {
                            KeyboardKey(
                                modifier = Modifier.size(naturalsSize),
                                color = naturalsColor,
                                indication = indication,
                                onClick = onNaturalKeyClick,
                            )
                        }
                    }
                    // Accidentals
                    var x = 0.dp
                    val offset = (accidentalsSize.width + space) / 2f
                    val indication = rememberRipple(color = accidentalsIndicationColor)
                    repeat(accidentals) { i ->
                        val dist = NaturalsDistances[i]
                        x += (naturalsSize.width + space) * dist
                        KeyboardKey(
                            modifier = Modifier
                                .size(accidentalsSize)
                                .offset(x = x - offset),
                            color = accidentalsColor,
                            indication = indication,
                            onClick = onAccidentalKeyClick,
                        )
                    }
                }
            }
        }
    }
}

private fun BoxWithConstraintsScope.calcKeyboardSize(octaves: Int): DpSize =
    if (maxWidth > maxHeight) {
        calcKeyboardSizeFromHeight(octaves)
            .takeUnless { it.width > maxWidth }
            ?: calcKeyboardSizeFromWidth(octaves)
    } else {
        calcKeyboardSizeFromWidth(octaves)
            .takeUnless { it.height > maxHeight }
            ?: calcKeyboardSizeFromHeight(octaves)
    }

private fun BoxWithConstraintsScope.calcKeyboardSizeFromWidth(octaves: Int): DpSize {
    val width = maxWidth
    return DpSize(
        width = width,
        height = width / octaves / OctaveWidthToHeightRatio,
    )
}

private fun BoxWithConstraintsScope.calcKeyboardSizeFromHeight(octaves: Int): DpSize {
    val height = maxHeight
    return DpSize(
        width = height * octaves * OctaveWidthToHeightRatio,
        height = height,
    )
}

@Composable
private fun KeyboardKey(
    modifier: Modifier,
    color: Color,
    cornerRadius: Dp = 3.dp,
    indication: Indication,
    onClick: () -> Unit,
) {
    // TODO: add 'drawLabels' Boolean parameter and draw labels
    val density = LocalDensity.current
    val radius = with(density) { CornerRadius(x = cornerRadius.toPx()) }
    val shape = RoundedCornerShape(cornerRadius)
    Canvas(
        modifier = modifier
            .clip(shape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = indication,
                onClick = onClick,
            ),
    ) {
        drawRoundRect(
            color = color,
            cornerRadius = radius,
        )
    }
}

private val NaturalsDistances = listOf(1, 1, 2, 1, 1) // successived number of natural keys to the next accidental key

private const val OctaveWidthToHeightRatio = 11f / 10
private const val AccidentalsToNaturalsHeightRatio = 2f / 3
private const val AccidentalsToNaturalsWidthRatio = 13f / 23