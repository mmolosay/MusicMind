package io.github.mmolosay.musicmind.theory.tuning

import io.github.mmolosay.musicmind.theory.instruments.DiscretePitchInstrument
import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.tuning.instrument.InstrumentTuning
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem

class PitchProducer(
    private val pitchSequencer: PitchSequencer,
) {

    fun DiscretePitchInstrument.pitches(): List<Pitch> =
        pitches(
            amount = keys,
            tuningSystem = tuningSystem,
            instrumentTuning = tuning,
        )

    fun pitches(
        amount: Int,
        tuningSystem: TuningSystem,
        instrumentTuning: InstrumentTuning,
    ): List<Pitch> =
        with(pitchSequencer) { tuningSystem + instrumentTuning }
            .take(amount)
            .toList()
}