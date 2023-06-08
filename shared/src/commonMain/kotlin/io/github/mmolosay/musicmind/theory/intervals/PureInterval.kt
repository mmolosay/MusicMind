package io.github.mmolosay.musicmind.theory.intervals

import io.github.mmolosay.musicmind.utils.Ratio
import io.github.mmolosay.musicmind.theory.cents.Cents
import io.github.mmolosay.musicmind.theory.perception.Stability
import io.github.mmolosay.musicmind.utils.result
import io.github.mmolosay.musicmind.utils.to
import kotlin.math.log2

internal enum class PureInterval(
    val ratio: Ratio,
    val stability: Stability,
) {

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

    val cents: Cents by lazy {
        Cents.Octave * log2(ratio.result)
    }
}