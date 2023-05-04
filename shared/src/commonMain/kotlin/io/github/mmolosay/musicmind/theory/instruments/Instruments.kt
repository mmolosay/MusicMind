package io.github.mmolosay.musicmind.theory.instruments

import io.github.mmolosay.musicmind.theory.tuning.TuningSystem
import io.github.mmolosay.musicmind.theory.tuning.TuningSystems

object Instruments {

    fun Piano(
        tuning: TuningSystem = TuningSystems.EqualTemperament12Tone(),
    ): Instrument =
        InstrumentImpl(
            tuning = tuning,
            keys = 88,
        )
}