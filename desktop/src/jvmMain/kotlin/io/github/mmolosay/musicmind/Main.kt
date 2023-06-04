package io.github.mmolosay.musicmind

import io.github.mmolosay.musicmind.presentation.ui.launchApplication
import io.github.mmolosay.musicmind.theory.context.MusicContext
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.key
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.open
import io.github.mmolosay.musicmind.theory.modes.Mode
import io.github.mmolosay.musicmind.theory.partition.OctavePartitions.partition
import io.github.mmolosay.musicmind.theory.partition.over
import io.github.mmolosay.musicmind.theory.pitch.transpose
import io.github.mmolosay.musicmind.theory.scales.withOctaveEntry

fun main() {
    makePianoMusic()
    makeGuitarMusic()
    launchApplication()
}

private fun makePianoMusic() {
    MusicContext(
        instrument = { Piano() },
    ).run {
        val key = 49.key
        val majorPartition = instrument.tuningSystem.partition(2, 2, 1, 2, 2, 2, 1)
        val scale = with(instrument) { key.scale(majorPartition) }
        val lydian = majorPartition over Mode.Lydian
        println()
    }
}

private fun makeGuitarMusic() {
    MusicContext(
        instrument = { Guitar() },
    ).run {
        val keys = instrument.keys
        val key = keys.strings.last().open()

        val majorPartition = instrument.tuningSystem.partition(2, 2, 1, 2, 2, 2, 1)
        val scale = with(instrument) { key.scale(majorPartition) }
        val pitches = with(instrument) { scale.pitches() }
        val pitchesWithOctave = pitches.withOctaveEntry()

        val pitch1 = with(instrument) { 3.key.pitch }
        val pitch2 = with(instrument) { 15.key.pitch }
        val pitch1mutiplied = pitch1 * 2

        val result1 = pitch1.transpose(+2)
        val result2 = pitch1.transpose(-2)
        val result3 = pitch1.transpose(0)
        val result4 = pitch1.transpose(+1)
    }
}