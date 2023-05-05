package io.github.mmolosay.musicmind.theory.instruments.tuner

import io.github.mmolosay.musicmind.theory.instruments.Instrument
import io.github.mmolosay.musicmind.theory.tuning.instrument.InstrumentTuning

/**
 * Tunes [Instrument] using provided [InstrumentTuning].
 */
fun interface Tuner {

    /**
     * Tunes receiver [Instrument] according to its tuning system (if any) and specified [tuning].
     *
     * Since [Instrument] instances are immutable, returns modified copy of the receiver intrument.
     */
    fun Instrument.tune(tuning: InstrumentTuning): Instrument
}