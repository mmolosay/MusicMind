package io.github.mmolosay.musicmind.theory.instruments

import io.github.mmolosay.musicmind.theory.tuning.instrument.InstrumentTuning
import io.github.mmolosay.musicmind.theory.pitch.Pitch

/**
 * A continuous pitch instruments are those that made to produce any (microtonal) pitch in its range.
 */
data class ContinuousPitchInstrument internal constructor(
    override val tuning: InstrumentTuning,
) : Instrument {

    override val range: ClosedRange<Pitch>
        get() = TODO()
}