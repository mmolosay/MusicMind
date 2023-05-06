package io.github.mmolosay.musicmind.theory.tuning.instrument

import io.github.mmolosay.musicmind.theory.pitch.Pitch

data class FretboardTuning(
    val openStrings: List<Pitch>,
) : InstrumentTuning