package io.github.mmolosay.musicmind.theory.pitch

import io.github.mmolosay.musicmind.theory.tuning.PitchCalculator.closestAccuratePitchTo
import io.github.mmolosay.musicmind.theory.tuning.PitchCalculator.closestNaturalPriorTo
import io.github.mmolosay.musicmind.theory.tuning.system.EqualTemperament
import io.github.mmolosay.musicmind.theory.tuning.system.PureIntonation
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem

object PitchLabels {

    infix fun TuningSystem.label(pitch: Pitch): Label =
        when (this) {
            is EqualTemperament -> label(pitch)
            is PureIntonation -> TODO()
        }

    fun EqualTemperament.label(pitch: Pitch): Label {
        val closestAccuratePitch = closestAccuratePitchTo(pitch)
        val result = closestNaturalPriorTo(closestAccuratePitch)
        val isAccurate = pitch isPercievablyEqualTo closestAccuratePitch
        return Label(
            natural = result.natural,
            alteration = (-result.stepsDistance).sharp,
            isAccurate = isAccurate,
        )
    }
}