package io.github.mmolosay.musicmind.theory.scales

import io.github.mmolosay.musicmind.theory.intervals.PureInterval

internal object PureScales {

    val Ionian by lazy {
        listOf(
            PureInterval.SecondMajor,
            PureInterval.ThirdMajor,
            PureInterval.Fourth,
            PureInterval.Fifth,
            PureInterval.SixthMajor,
            PureInterval.SeventhMajor,
            PureInterval.Octave,
        )
    }
}