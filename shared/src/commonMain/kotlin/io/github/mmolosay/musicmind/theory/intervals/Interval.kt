package io.github.mmolosay.musicmind.theory.intervals

/**
 * Music interval, defined by a [steps] difference between two pitched.
 *
 * [Interval – Wikipedia](https://en.wikipedia.org/wiki/Interval_(music))
 */
sealed interface Interval {
    val steps: Int
}