package io.github.mmolosay.musicmind.theory.instruments

import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.tuning.PitchProducer
import io.github.mmolosay.musicmind.theory.tuning.instrument.InstrumentTuning
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem

/**
 * A chromatic instruments are those that made to produce the chromatic scale.
 *
 * [Chromatic scale, musical instruments – Wikipedia](https://en.wikipedia.org/wiki/Diatonic_and_chromatic#Musical_instruments)
 */
data class ChromaticInstrument internal constructor(
    val keys: Int,
    val tuningSystem: TuningSystem,
    override val tuning: InstrumentTuning,
    private val pitchProducer: PitchProducer,
) : Instrument {

    val pitches: List<Pitch> by lazy {
        with(pitchProducer) { pitches() }
    }

    override val range: ClosedRange<Pitch>
        get() = pitches.first()..pitches.last()

    val Key.pitch: Pitch
        get() = pitches[ordinal]

    /**
     * Represents a specific position on an instrument.
     * It is a key on a piano and fret of string on a guitar.
     *
     * A strategy of obtaining a set of all instrument's keys in correct order is provided by the instrument itself.
     */
    @JvmInline
    value class Key(val ordinal: Int) {

        init {
            require(ordinal > 0) { "Key ordinal must be greater than zero" }
        }
    }
}