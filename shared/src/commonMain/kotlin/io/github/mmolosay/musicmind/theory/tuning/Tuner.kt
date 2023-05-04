package io.github.mmolosay.musicmind.theory.tuning

import io.github.mmolosay.musicmind.theory.pitch.Pitch
import java.math.BigDecimal
import java.text.DecimalFormat
import kotlin.math.pow

object Tuner {

    /** A format that removes decimal digits after first 6 */
    val format = DecimalFormat("#.######")

    fun tune(
        keys: Int,
        tuning: TuningSystem,
    ): List<Pitch> =
        when (tuning) {
            is TuningSystem.EqualTemperament -> tuning.use(keys)
        }

    // doesn't consider inharmonicity
    fun TuningSystem.EqualTemperament.use(keys: Int): List<Pitch> {
        val oneStepFrequencyRatio = OctaveRatio.pow(1.0 / pitchClasses)
        return List(keys) { i ->
            val ordinal = i + 1
            val power = ordinal - A4Ordinal
            val a4Offset = oneStepFrequencyRatio.pow(power).toBigDecimal()
            val hz = format.format(a4Offset * A4Frequency).toDouble()
            Pitch(hz)
        }
    }

    /** Ratio between two pitches in an octave distance (octave equivalence). */
    private const val OctaveRatio = 2.0

    /** Frequency of A4 key of a piano, tuned in 12-TET tuning system. */
    private val A4Frequency = BigDecimal("440")

    /** Ordinal of A4 key on an 88-key piano. */
    private const val A4Ordinal = 49
}