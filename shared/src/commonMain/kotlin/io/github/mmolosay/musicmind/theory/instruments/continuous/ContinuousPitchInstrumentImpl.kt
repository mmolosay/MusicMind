package io.github.mmolosay.musicmind.theory.instruments.continuous

import io.github.mmolosay.musicmind.theory.tuning.instrument.InstrumentTuning

data class ContinuousPitchInstrumentImpl internal constructor(
    override val tuning: InstrumentTuning,
) : AbstractContinuousPitchInstrument()