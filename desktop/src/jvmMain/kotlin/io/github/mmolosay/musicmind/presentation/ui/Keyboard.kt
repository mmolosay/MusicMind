package io.github.mmolosay.musicmind.presentation.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import io.github.mmolosay.musicmind.presentation.ui.KeyboardDefaults.Colors
import io.github.mmolosay.musicmind.presentation.ui.KeyboardDefaults.DrawLabel
import io.github.mmolosay.musicmind.presentation.ui.KeyboardDefaults.DrawLabels
import io.github.mmolosay.musicmind.presentation.ui.KeyboardDefaults.NaturalsSpacing
import io.github.mmolosay.musicmind.presentation.ui.design.MusicMindTheme
import io.github.mmolosay.musicmind.theory.label.Label

@Composable
fun Keyboard(
    modifier: Modifier,
    octaves: Int,
    onNaturalKeyClick: () -> Unit,
    onAccidentalKeyClick: () -> Unit,
    drawLabels: DrawLabels = DrawLabels.No,
    colors: Colors = KeyboardDefaults.colors(),
    spacing: NaturalsSpacing = NaturalsSpacing(),
) {
    BoxWithConstraints(
        modifier = modifier,
    ) {
        val keysPerOctave = 12
        val naturals = Label.Natural.Total
        val accidentals = keysPerOctave - naturals
        val totalNaturals = naturals * octaves
        val totalSpacings = totalNaturals - 1

        val size = calcKeyboardSize(octaves)
        var naturalsWidth = size.width / (totalNaturals + (spacing.naturalKeyWidthFraction * totalSpacings))
        var space = naturalsWidth * spacing.naturalKeyWidthFraction
        if (space > spacing.max) { // recalculate naturals width
            space = spacing.max
            naturalsWidth = (size.width - (space * totalSpacings)) / totalNaturals
        }
        val naturalsSize = DpSize(naturalsWidth, size.height)
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
                        val indication = rememberRipple(color = colors.naturalsIndication)
                        repeat(naturals) { i ->
                            val drawLabel = when (drawLabels) {
                                is DrawLabels.No -> DrawLabel.No
                                is DrawLabels.Yes -> DrawLabel.Yes(drawLabels.naturals[i], colors.naturalsLabel)
                            }
                            KeyboardKey(
                                size = naturalsSize,
                                color = colors.naturals,
                                drawLabel = drawLabel,
                                indication = indication,
                                onClick = onNaturalKeyClick,
                            )
                        }
                    }
                    // Accidentals
                    var x = 0.dp
                    val offset = (accidentalsSize.width + space) / 2f
                    val indication = rememberRipple(color = colors.accidentalsIndication)
                    repeat(accidentals) { i ->
                        val drawLabel = when (drawLabels) {
                            is DrawLabels.No -> DrawLabel.No
                            is DrawLabels.Yes -> DrawLabel.Yes(drawLabels.accidentals[i], colors.accidentalsLabel)
                        }
                        val dist = NaturalsDistances[i]
                        x += (naturalsSize.width + space) * dist
                        KeyboardKey(
                            modifier = Modifier.offset(x = x - offset),
                            size = accidentalsSize,
                            color = colors.accidentals,
                            drawLabel = drawLabel,
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
    modifier: Modifier = Modifier,
    size: DpSize,
    color: Color,
    cornerRadius: Dp = 3.dp,
    drawLabel: DrawLabel,
    indication: Indication,
    onClick: () -> Unit,
) {
    val density = LocalDensity.current
    val radius = with(density) { CornerRadius(x = cornerRadius.toPx()) }
    val shape = RoundedCornerShape(cornerRadius)
    Box(
        modifier = modifier
            .clip(shape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = indication,
                onClick = onClick,
            ),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Canvas(modifier = Modifier.size(size)) {
            drawRoundRect(
                color = color,
                cornerRadius = radius,
            )
        }
        if (drawLabel is DrawLabel.Yes) {
            Column {
                Text(text = drawLabel.label, color = drawLabel.color)
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

object KeyboardDefaults {

    @Composable
    fun colors(
        naturals: Color = MusicMindTheme.colors.keyboardNaturals,
        naturalsLabel: Color = Color.LightGray, // TODO
        naturalsIndication: Color = MusicMindTheme.colors.keyboardNaturalsIndication,
        accidentals: Color = MusicMindTheme.colors.keyboardAccidentals,
        accidentalsLabel: Color = Color.LightGray, // TODO
        accidentalsIndication: Color = MusicMindTheme.colors.keyboardAccidentalsIndication,
    ): Colors =
        Colors(
            naturals = naturals,
            naturalsLabel = naturalsLabel,
            naturalsIndication = naturalsIndication,
            accidentals = accidentals,
            accidentalsLabel = accidentalsLabel,
            accidentalsIndication = accidentalsIndication,
        )

    data class Colors constructor(
        val naturals: Color,
        val naturalsLabel: Color,
        val naturalsIndication: Color,
        val accidentals: Color,
        val accidentalsLabel: Color,
        val accidentalsIndication: Color,
    )

    data class NaturalsSpacing(
        val naturalKeyWidthFraction: Float = 0.04f,
        val max: Dp = 6.dp,
    )

    sealed interface DrawLabels {
        object No : DrawLabels
        data class Yes(val naturals: List<String>, val accidentals: List<String>) : DrawLabels
    }

    sealed interface DrawLabel {
        object No : DrawLabel
        data class Yes(val label: String, val color: Color) : DrawLabel
    }
}

private val NaturalsDistances = listOf(1, 1, 2, 1, 1) // successived number of natural keys to the next accidental key

private const val OctaveWidthToHeightRatio = 11f / 10
private const val AccidentalsToNaturalsHeightRatio = 2f / 3
private const val AccidentalsToNaturalsWidthRatio = 13f / 23