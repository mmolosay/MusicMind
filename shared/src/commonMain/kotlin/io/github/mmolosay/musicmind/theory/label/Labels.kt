package io.github.mmolosay.musicmind.theory.label

import io.github.mmolosay.musicmind.theory.cents.Cents
import io.github.mmolosay.musicmind.theory.cents.cents
import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.pitch.isPercievablyEqualTo
import io.github.mmolosay.musicmind.theory.tuning.PitchCalculator.closestAccuratePitchTo
import io.github.mmolosay.musicmind.theory.tuning.PitchCalculator.closestNaturalPriorTo
import io.github.mmolosay.musicmind.theory.tuning.system.EqualTemperament
import io.github.mmolosay.musicmind.theory.tuning.system.PureIntonation
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem

object Labels {

    infix fun TuningSystem.label(pitch: Pitch): Label =
        when (this) {
            is EqualTemperament -> label(pitch)
            is PureIntonation -> TODO()
        }

    fun EqualTemperament.label(pitch: Pitch): Label {
        val closestAccuratePitch = closestAccuratePitchTo(pitch)
        val result = closestNaturalPriorTo(closestAccuratePitch)
        val deviation = if (pitch isPercievablyEqualTo closestAccuratePitch) {
            Cents.Zero // cut off difference if it is not human percievable
        } else {
            (pitch - closestAccuratePitch).cents // granted greater than Constants.PitchEqualityThreshold
        }
        return Label(
            natural = result.natural,
            alteration = (-result.stepsDistance).sharp,
            deviation = deviation
        )
    }
}