package io.github.mmolosay.musicmind.theory.intervals

/**
 * Music interval, defined by a number of [keys] between two notes, produced by an instrument in specific tuning system.
 *
 * Those [keys] are a number of "positions" between said notes.
 *
 * [Interval – Wikipedia](https://en.wikipedia.org/wiki/Interval_(music))
 */
@JvmInline
value class KeyInterval internal constructor(val keys: Int)

val Int.keys: KeyInterval
    get() = KeyInterval(keys = this)