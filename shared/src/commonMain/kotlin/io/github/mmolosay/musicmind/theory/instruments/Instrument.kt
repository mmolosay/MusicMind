package io.github.mmolosay.musicmind.theory.instruments

import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.tuning.instrument.InstrumentTuning

/**
 * An immutable abstraction of real-world musical instrument.
 *
 * Instrument is always tuned.
 */
sealed interface Instrument {

    val tuning: InstrumentTuning
    val range: ClosedRange<Pitch>

    val Pitch.exists: Boolean
        get() = this in range
}