package io.github.mmolosay.musicmind.theory.tuning.instrument

import io.github.mmolosay.musicmind.theory.pitch.Pitch

data class FretboardTuning internal constructor(
    val openStrings: List<Pitch>,
) : InstrumentTuning {

    init {
        require(openStrings.isNotEmpty()) { "Can\'t create empty FretboardTuning" }
    }
}

fun List<Pitch>.asFretboardTuning(): FretboardTuning =
    FretboardTuning(openStrings = this)