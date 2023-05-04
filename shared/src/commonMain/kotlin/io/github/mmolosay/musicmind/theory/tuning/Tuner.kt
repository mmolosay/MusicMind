package io.github.mmolosay.musicmind.theory.tuning

import io.github.mmolosay.musicmind.theory.pitch.Pitch
import kotlin.math.pow

object Tuner {

    fun tune(
        keys: Int,
        tuning: TuningSystem,
    ): List<Pitch> =
        when (tuning) {
            is TuningSystem.EqualTemperament -> tuning.use(keys)
        }

    // doesn't count inharmonicity
    fun TuningSystem.EqualTemperament.use(keys: Int): List<Pitch> {
        val oneStepFrequencyRatio = OctaveRatio.pow(1.0 / keysPerOctave)
        return List(keys) { i ->
            val ordinal = i + 1
            val power = ordinal - A4Ordinal
            val a4Offset = oneStepFrequencyRatio.pow(power)
            val hz = a4Offset * A4Frequency
            Pitch(hz)
        }
    }

    private const val OctaveRatio = 2.0
    private const val A4Frequency = 440
    private const val A4Ordinal = 49
}