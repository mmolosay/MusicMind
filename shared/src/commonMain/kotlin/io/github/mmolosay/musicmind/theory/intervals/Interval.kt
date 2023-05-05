package io.github.mmolosay.musicmind.theory.intervals

/**
 * Music interval, defined by a number of [steps] between two notes, produced by an instrument in specific tuning system.
 *
 * Those [steps] are a number of "positions" between said notes
 *
 * [Interval – Wikipedia](https://en.wikipedia.org/wiki/Interval_(music))
 */
sealed interface Interval {
    val steps: Int
}