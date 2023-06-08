package io.github.mmolosay.musicmind.theory.pitch

import io.github.mmolosay.musicmind.theory.tuning.PitchCalculator.closestAccuratePitchTo
import io.github.mmolosay.musicmind.theory.tuning.PitchCalculator.closestNaturalPriorTo
import io.github.mmolosay.musicmind.theory.tuning.system.EqualTemperament
import io.github.mmolosay.musicmind.theory.tuning.system.PureIntonation
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem

object PitchLabels {

    infix fun TuningSystem.label(pitch: Pitch): PitchLabel =
        when (this) {
            is EqualTemperament -> label(pitch)
            is PureIntonation -> TODO()
        }

    fun EqualTemperament.label(pitch: Pitch): PitchLabel {
        val closestAccuratePitch = closestAccuratePitchTo(pitch)
        val result = closestNaturalPriorTo(closestAccuratePitch)
        val isAccurate = pitch isPercievablyEqualTo closestAccuratePitch
        return PitchLabel(
            natural = result.natural,
            alteration = PitchLabel.Alteration.Sharp,
            alterationAmount = -result.stepsDistance,
            isAccurate = isAccurate,
        )
    }
}