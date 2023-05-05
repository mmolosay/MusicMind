package io.github.mmolosay.musicmind.theory.intervals

/**
 * Music interval, defined by a number of [steps] between two notes, produced by tuning system.
 *
 * Those [steps] are a number of the intervals between two adjacent notes in a tuning system.
 * For instance, in 12-TET it is a semitone.
 *
 * [Interval – Wikipedia](https://en.wikipedia.org/wiki/Interval_(music))
 */
sealed interface Interval {
    val steps: Int
}