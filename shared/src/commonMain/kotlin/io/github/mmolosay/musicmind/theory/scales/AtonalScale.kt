package io.github.mmolosay.musicmind.theory.scales

import io.github.mmolosay.musicmind.theory.distance.Distance

/**
 * A scale without specific tonic, represented by the set of successive distances.
 *
 * [Scale – Wikipedia](https://en.wikipedia.org/wiki/Scale_(music))
 */
interface AtonalScale {
    val intervals: List<Distance>
}

val AtonalScale.size: Int
    get() = intervals.size

