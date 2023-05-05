package io.github.mmolosay.musicmind.theory.tuning.system

import io.github.mmolosay.musicmind.theory.intervals.Interval

/**
 * [Just intonation – Wikipedia](https://en.wikipedia.org/wiki/Just_intonation)
 */
class PureIntonation(
    override val pitchClasses: Int,
    override val step: TuningSystem.Step.Variable,
) : TuningSystem {

    override val majorIntervals: List<Interval>
        get() = TODO("Not yet implemented")
}