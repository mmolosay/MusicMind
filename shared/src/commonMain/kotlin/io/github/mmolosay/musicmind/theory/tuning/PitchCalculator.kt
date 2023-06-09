package io.github.mmolosay.musicmind.theory.tuning

import io.github.mmolosay.musicmind.theory.pitch.Label
import io.github.mmolosay.musicmind.theory.pitch.Pitch
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

/**
 * Performs calculations, related to frequencies and transposing.
 */
/*internal TODO*/ object PitchCalculator {

    /**
     * Adds number of [steps] to receiver pitch in terms of specified [tuningSystem].
     */
    fun Pitch.plusSteps(steps: Int, tuningSystem: EqualTemperament): Pitch =
        (frequency * tuningSystem.oneStepRatio.pow(steps)).toFloat().hz

    /**
     * Calculates distance between two pitches in given [EqualTemperament].
     * The said distance is measured in amount of steps between two spesified pitches.
     */
    fun EqualTemperament.stepsBetween(first: Pitch, second: Pitch): Double {
        val ratio = second.frequency.toBigDecimal().divide(
            first.frequency.toBigDecimal(), 8, RoundingMode.HALF_UP
        )
        val octaveFraction = log2(ratio.toDouble())
        return pitchClasses * octaveFraction
    }

    /**
     * Finds closest `'accurate'` pitch to the specified [pitch] in terms of receiver [EqualTemperament].
     *
     * The `'accurate'` pitch is a pitch that occurs in the tuning system.
     * For instance, in 12-TET, there is a pitch of `440 Hz` (A4). It is an `'accurate'` pitch.
     * At the same time, there's no pitch of `442 Hz` there. It is not an `'accurate'` pitch.
     */
    fun EqualTemperament.closestAccuratePitchTo(pitch: Pitch): Pitch {
        val reference = Tunings.A4ConcertFrequency
        val referencePitch = reference.toFloat().hz
        if (pitch isPercievablyEqualTo referencePitch) return referencePitch
        val ratio = pitch.frequency.toBigDecimal().divide(
            reference, 8, RoundingMode.HALF_UP
        )
        val octaveFraction = log2(ratio.toDouble())
        val steps = round(pitchClasses * octaveFraction)
        val frequency = reference * 2.0.pow(steps / pitchClasses).toBigDecimal()
        return frequency.toFloat().hz
    }

    /**
     * Determines, whether the receiver [Pitch] is of any [Label.Natural] pitch class.
     */
    fun Pitch.isNatural(): Boolean =
        asNatural() != null

    /**
     * Check the receiver [Pitch] for belonging to any [Label.Natural] pitch class.
     */
    fun Pitch.asNatural(): Label.Natural? {
        for ((natural, pitch) in NaturalsReference) {
            if (this isOfSamePitchClassAs pitch) return natural
        }
        return null
    }

    /**
     * Finds the closest natural pitch, that's positioned __before__ the specified [pitch].
     * If [pitch] is a natural itself, returns it.
     */
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
        val natural: Label.Natural,
        val stepsDistance: Int,
    )

    /**
     * [Piano key frequencies – Wikipedia](https://en.wikipedia.org/wiki/Piano_key_frequencies)
     */
    private val NaturalsReference by lazy {
        mapOf(
            Label.Natural.A to 440.0000f.hz,
            Label.Natural.B to 493.8833f.hz,
            Label.Natural.C to 523.2511f.hz,
            Label.Natural.D to 587.3295f.hz,
            Label.Natural.E to 659.2551f.hz,
            Label.Natural.F to 698.4565f.hz,
            Label.Natural.G to 783.9909f.hz,
        )
    }
}