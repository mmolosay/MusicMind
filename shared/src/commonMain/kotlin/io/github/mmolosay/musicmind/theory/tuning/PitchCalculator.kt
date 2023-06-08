package io.github.mmolosay.musicmind.theory.tuning

import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.pitch.PitchLabel
import io.github.mmolosay.musicmind.theory.pitch.hz
import io.github.mmolosay.musicmind.theory.pitch.isOfSamePitchClassAs
import io.github.mmolosay.musicmind.theory.pitch.isPercievablyEqualTo
import io.github.mmolosay.musicmind.theory.tuning.instrument.Tunings
import io.github.mmolosay.musicmind.theory.tuning.system.EqualTemperament
import io.github.mmolosay.musicmind.theory.tuning.system.oneStepRatio
import java.math.RoundingMode
import kotlin.math.log2
import kotlin.math.pow
import kotlin.math.round

/*internal TODO*/ object PitchCalculator {

    fun Pitch.plusSteps(steps: Int, tuningSystem: EqualTemperament): Pitch =
        (frequency * tuningSystem.oneStepRatio.pow(steps)).toFloat().hz

    /**
     * Calculates distance between two pitches in given [EqualTemperament].
     */
    fun EqualTemperament.stepsBetween(first: Pitch, second: Pitch): Double {
        val ratio = second.frequency.toBigDecimal().divide(
            first.frequency.toBigDecimal(), 8, RoundingMode.HALF_UP
        )
        val octaveFraction = log2(ratio.toDouble())
        return pitchClasses * octaveFraction
    }

    fun EqualTemperament.closestAccuratePitchTo(other: Pitch): Pitch {
        val reference = Tunings.A4ConcertFrequency
        val referencePitch = reference.toFloat().hz
        if (other isPercievablyEqualTo referencePitch) return referencePitch
        val ratio = other.frequency.toBigDecimal().divide(
            reference, 8, RoundingMode.HALF_UP
        )
        val octaveFraction = log2(ratio.toDouble())
        val steps = round(pitchClasses * octaveFraction)
        val frequency = reference * 2.0.pow(steps / pitchClasses).toBigDecimal()
        return frequency.toFloat().hz
    }

    fun Pitch.isNatural(): Boolean =
        asNatural() != null

    fun Pitch.asNatural(): PitchLabel.Natural? {
        for ((natural, pitch) in NaturalsReference) {
            if (this isOfSamePitchClassAs pitch) return natural
        }
        return null
    }

    fun EqualTemperament.closestNaturalPriorTo(pitch: Pitch): ClosestNaturalResult {
        pitch.asNatural()?.let { return ClosestNaturalResult(it, 0) }
        var steps = -1
        while (true) {
            val other = pitch.plusSteps(steps, this)
            other.asNatural()?.let { return ClosestNaturalResult(it, steps) }
            steps--
        }
    }

    data class ClosestNaturalResult(
        val natural: PitchLabel.Natural,
        val stepsDistance: Int,
    )

    private val NaturalsReference by lazy {
        listOf(
            PitchLabel.Natural.A to 440.0000f.hz,
            PitchLabel.Natural.B to 493.8833f.hz,
            PitchLabel.Natural.C to 523.2511f.hz,
            PitchLabel.Natural.D to 587.3295f.hz,
            PitchLabel.Natural.E to 659.2551f.hz,
            PitchLabel.Natural.F to 698.4565f.hz,
            PitchLabel.Natural.G to 783.9909f.hz,
        )
    }
}