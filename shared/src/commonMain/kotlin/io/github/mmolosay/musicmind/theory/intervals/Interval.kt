package io.github.mmolosay.musicmind.theory.intervals

import io.github.mmolosay.musicmind.theory.Ratio
import io.github.mmolosay.musicmind.theory.perception.Stability

interface Interval {
    val ratio: Ratio
    val stability: Stability
}