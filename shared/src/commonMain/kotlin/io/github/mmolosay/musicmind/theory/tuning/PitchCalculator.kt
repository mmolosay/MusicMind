package io.github.mmolosay.musicmind.theory.tuning

import io.github.mmolosay.musicmind.theory.label.Label
import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.pitch.hz
import io.github.mmolosay.musicmind.theory.pitch.isOfSamePitchClassAs
import io.github.mmolosay.musicmind.theory.pitch.isPercievablyEqualTo
import io.github.mmolosay.musicmind.theory.tuning.instrument.Tunings.A4ConcertPitch
import io.github.mmolosay.musicmind.theory.tuning.system.EqualTemperament
import io.github.mmolosay.musicmind.theory.tuning.system.oneStepRatio
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import kotlin.math.log2
import kotlin.math.pow
import kotlin.math.round

/**
 * Performs calculations, related to frequencies and transposing.
 */
internal object PitchCalculator {

    val A4ConcertFrequency by lazy { 440.toBigDecimal(c) }

    private val rounding = RoundingMode.HALF_UP
    private val c = MathContext(15, rounding)

    /**
     * Adds number of [steps] to receiver pitch in terms of specified [tuningSystem].
     */
    fun Pitch.plusSteps(steps: Int, tuningSystem: EqualTemperament): Pitch {
        val hz = frequency.toBigDecimal()
        val multiplier = tuningSystem.oneStepRatio.pow(steps).toBigDecimal()
        val result = hz.multiply(multiplier, c).setPitchScale()
        return result.toFloat().hz
    }

    /**
     * Calculates distance between two pitches in given [EqualTemperament].
     * The said distance is measured in amount of steps between two spesified pitches.
     */
    fun EqualTemperament.stepsBetween(pitch1: Pitch, pitch2: Pitch): Double {
        val hz1 = pitch1.frequency.toBigDecimal()
        val hz2 = pitch2.frequency.toBigDecimal()
        val ratio = hz2.divide(hz1, c)
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
        val reference = A4ConcertFrequency
        if (pitch isPercievablyEqualTo A4ConcertPitch) return A4ConcertPitch

        val ratio = pitch.frequency.toBigDecimal().divide(reference, c)
        val octaveFraction = log2(ratio.toDouble())
        val steps = round(pitchClasses * octaveFraction).toInt()
        val multiplier = oneStepRatio.pow(steps).toBigDecimal(c)
        val frequency = reference.multiply(multiplier, c).setPitchScale()
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

    private fun BigDecimal.setPitchScale() =
        setScale(5, rounding)

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