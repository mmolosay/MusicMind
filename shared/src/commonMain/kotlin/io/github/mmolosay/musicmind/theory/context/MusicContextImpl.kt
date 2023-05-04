package io.github.mmolosay.musicmind.theory.context

import io.github.mmolosay.musicmind.theory.instruments.Instrument
import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.scales.Scale

class MusicContextImpl(
    override val instrument: Instrument
) : MusicContext {

    override fun Scale.pitches(): List<Pitch> {
        val tonicOrdinal = tonic.steps
        val tonicPitch = instrument.pitches[tonicOrdinal]
        val pitches = mutableListOf(tonicPitch)
        var lastOrdinal = tonicOrdinal
        intervals.forEachIndexed { i, interval ->
            val noteOrdinal = lastOrdinal + interval.steps
            pitches.add(instrument.pitches[noteOrdinal])
            lastOrdinal = noteOrdinal
        }
        return pitches
    }
}