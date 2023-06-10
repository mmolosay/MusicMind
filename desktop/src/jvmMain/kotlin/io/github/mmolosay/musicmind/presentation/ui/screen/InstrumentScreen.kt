package io.github.mmolosay.musicmind.presentation.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.mmolosay.musicmind.presentation.DefaultNoteFormatter
import io.github.mmolosay.musicmind.presentation.Strings
import io.github.mmolosay.musicmind.presentation.resources.localizedString
import io.github.mmolosay.musicmind.presentation.ui.Keyboard
import io.github.mmolosay.musicmind.presentation.ui.KeyboardDefaults
import io.github.mmolosay.musicmind.presentation.ui.KeyboardDefaults.DrawLabels
import io.github.mmolosay.musicmind.presentation.ui.components.Screen
import io.github.mmolosay.musicmind.theory.context.MusicContext
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.accidentals
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.naturals
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.range
import io.github.mmolosay.musicmind.theory.tuning.instrument.KeyboardTuning

@Composable
fun InstrumentScreen() =
    Screen {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            title()
            keyboard()
        }
    }

private fun LazyListScope.title() {
    item(
        key = "title",
    ) {
        Text(
            text = localizedString(Strings.InstrumentsScreenTitle),
            style = MaterialTheme.typography.displaySmall,
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

private fun LazyListScope.keyboard() =
    item(
        key = "keyboard",
    ) {
        val tuning = KeyboardTuning(466.16376f)
        val context = remember { MusicContext { Piano(instrumentTuning = tuning) } }
        val labelFormatter = remember { DefaultNoteFormatter() }

        val octaves = 2
        val instrument = context.instrument
        val keysPerOctave = instrument.tuningSystem.pitchClasses
        val totalKeys = keysPerOctave * octaves

        val keys = remember(context) { instrument.keys.range(28, totalKeys) }
        val naturals = remember(context) {
            keys.naturals()
                .map { with(instrument) { it.label } }
                .map { with(labelFormatter) { it.display } }
        }
        val accidentals = remember(context) {
            keys.accidentals().first() // take only accidentals of rank=1
                .map { with(instrument) { it.label } }
                .map { with(labelFormatter) { it.display } }
        }
        Keyboard(
            modifier = Modifier.height(400.dp),
            octaves = octaves,
            onNaturalKeyClick = { println("White key!") },
            onAccidentalKeyClick = { println("Black key!") },
            drawLabels = DrawLabels.Yes(naturals, accidentals)
        )
    }