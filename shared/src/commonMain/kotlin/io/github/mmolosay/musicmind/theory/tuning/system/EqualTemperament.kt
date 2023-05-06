package io.github.mmolosay.musicmind.theory.tuning.system

import io.github.mmolosay.musicmind.theory.Constants
import io.github.mmolosay.musicmind.theory.cents.Cents
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem.Step
import kotlin.math.pow

/**
 * [Equal temperament – Wikipedia](https://en.wikipedia.org/wiki/Equal_temperament)
 */
class EqualTemperament(
    override val pitchClasses: Int,
) : TuningSystem {

    override val majorIntervals by lazy {
        val multiplier = pitchClasses / ReferencePitchClasses
        listOf(
            FullStep, FullStep, HalfStep, FullStep, FullStep, FullStep, HalfStep,
        ).map { }
        TODO()
        // 5.16666 & 2.583333
        // 12-TET: 2 2 1 2 2 2 1, sum=12
        // 31-TET: 5 5 3 5 5 5 3, sum=31
    }

    override val step by lazy {
        Step.Constant(Cents.Octave / pitchClasses)
    }

    private companion object {
        const val ReferencePitchClasses = 12
        const val FullStep = 200
        const val HalfStep = FullStep / 2
    }
}

val EqualTemperament.oneStepRatio: Double
    get() = Constants.OctaveRatio.pow(1.0 / pitchClasses) // pitchClasses-th root of 2 (octave ratio)