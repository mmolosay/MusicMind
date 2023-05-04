package io.github.mmolosay.musicmind.theory.intervals

/**
 * An implementation of [Interval] that specifies an order in which two pitches are played.
 * If interval is [ascending] then second note is higher in pitch (or similar).
 */
class DirectedInterval(
    override val steps: Int,
    val ascending: Boolean,
) : Interval