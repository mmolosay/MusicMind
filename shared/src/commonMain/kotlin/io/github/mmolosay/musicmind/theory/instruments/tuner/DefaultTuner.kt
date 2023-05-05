package io.github.mmolosay.musicmind.theory.instruments.tuner

import io.github.mmolosay.musicmind.theory.instruments.DiscretePitchInstrument
import io.github.mmolosay.musicmind.theory.instruments.ContinuousPitchInstrument
import io.github.mmolosay.musicmind.theory.instruments.Instrument
import io.github.mmolosay.musicmind.theory.instruments.discrete.DiscretePitchInstrumentImpl
import io.github.mmolosay.musicmind.theory.tuning.instrument.InstrumentTuning

class DefaultTuner : Tuner {

    override fun Instrument.tune(tuning: InstrumentTuning): Instrument =
        when (this) {
            is DiscretePitchInstrument -> tune(tuning)
            is ContinuousPitchInstrument -> tune(tuning)
        }

    private fun DiscretePitchInstrument.tune(tuning: InstrumentTuning): DiscretePitchInstrument =
        if (this is DiscretePitchInstrumentImpl) {
            copy(tuning = tuning)
        } else {
            error("Can\'t tune custom implementation of DiscretePitchInstrument")
        }

    private fun ContinuousPitchInstrument.tune(tuning: InstrumentTuning): ContinuousPitchInstrument {
        TODO()
    }
}