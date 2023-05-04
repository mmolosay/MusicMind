package io.github.mmolosay.musicmind.theory.scales

import io.github.mmolosay.musicmind.theory.intervals.Interval

/**
 * A scale without specific tonic, represented by the set of successive intervals.
 *
 * [Scale – Wikipedia](https://en.wikipedia.org/wiki/Scale_(music))
 */
interface AtonalScale {
    val intervals: List<Interval>
}

val AtonalScale.size: Int
    get() = intervals.size

