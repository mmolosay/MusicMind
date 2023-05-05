package io.github.mmolosay.musicmind

import io.github.mmolosay.musicmind.presentation.ui.launchApplication
import io.github.mmolosay.musicmind.theory.context.MusicContext
import io.github.mmolosay.musicmind.theory.modes.Mode
import io.github.mmolosay.musicmind.theory.partition.OctavePartitions.partition
import io.github.mmolosay.musicmind.theory.partition.asDistances
import io.github.mmolosay.musicmind.theory.partition.over

fun main() {
    MusicContext(
        instrument = { Piano() },
    ).run {
        val note = with(instrument) { notes.atKey { it.ordinal == 49 } }
        val majorPartition = instrument.tuningSystem.partition(2, 2, 1, 2, 2, 2, 1)
        val scale = with(instrument) { note.scale(majorPartition) }
        val lydian = majorPartition.over(Mode.Lydian)
        println()
    }
    launchApplication()
}