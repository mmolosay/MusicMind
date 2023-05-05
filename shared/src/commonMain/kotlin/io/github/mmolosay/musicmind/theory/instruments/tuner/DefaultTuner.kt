package io.github.mmolosay.musicmind.theory.instruments.tuner

import io.github.mmolosay.musicmind.theory.instruments.ChromaticInstrument
import io.github.mmolosay.musicmind.theory.instruments.ContinuousPitchInstrument
import io.github.mmolosay.musicmind.theory.instruments.Instrument
import io.github.mmolosay.musicmind.theory.tuning.instrument.InstrumentTuning

class DefaultTuner : Tuner {

    override fun Instrument.tune(tuning: InstrumentTuning): Instrument =
        when (this) {
            is ChromaticInstrument -> tune(tuning)
            is ContinuousPitchInstrument -> tune(tuning)
        }

    private fun ChromaticInstrument.tune(tuning: InstrumentTuning): ChromaticInstrument =
        copy(
            tuning = tuning,
        )

    private fun ContinuousPitchInstrument.tune(tuning: InstrumentTuning): ContinuousPitchInstrument {
        TODO()
    }
}