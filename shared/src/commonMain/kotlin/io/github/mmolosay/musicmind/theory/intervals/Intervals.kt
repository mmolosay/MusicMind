package io.github.mmolosay.musicmind.theory.intervals

import io.github.mmolosay.musicmind.theory.Ratio
import io.github.mmolosay.musicmind.theory.perception.Stability
import io.github.mmolosay.musicmind.theory.to

val Int.steps: Interval
    get() = UndirectedInterval(this)

fun List<Int>.asIntervals(): List<Interval> =
    map { UndirectedInterval(it) }

// TODO: provided intervals are pure.
//      actual, accurate ratio is defined by TuningSystem
enum class Intervals(
    override val ratio: Ratio,
    override val stability: Stability,
) : IntervalOld {

    Unison(
        ratio = 1 to 1,
        stability = Stability.PerfectConsonance,
    ),
    SecondMinor(
        ratio = 16 to 15,
        stability = Stability.PerfectDissonance,
    ),
    SecondMajor(
        ratio = 9 to 8,
        stability = Stability.PerfectDissonance,
    ),
    ThirdMinor(
        ratio = 6 to 5,
        stability = Stability.ImperfectDissonance,
    ),
    ThirdMajor(
        ratio = 5 to 4,
        stability = Stability.ImperfectConsonance,
    ),
    Fourth(
        ratio = 4 to 3,
        stability = Stability.PerfectConsonance,
    ),
    Tritone(
        ratio = 45 to 32,
        stability = Stability.Ambiguity,
    ),
    Fifth(
        ratio = 3 to 2,
        stability = Stability.PerfectConsonance,
    ),
    SixthMinor(
        ratio = 8 to 5,
        stability = Stability.ImperfectDissonance,
    ),
    SixthMajor(
        ratio = 5 to 3,
        stability = Stability.PerfectDissonance,
    ),
    SeventhMinor(
        ratio = 16 to 9,
        stability = Stability.ImperfectDissonance,
    ),
    SeventhMajor(
        ratio = 15 to 8,
        stability = Stability.PerfectDissonance,
    ),
    Octave(
        ratio = 2 to 1,
        stability = Stability.PerfectConsonance,
    ),
    ;
}