package io.github.mmolosay.musicmind.theory.intervals

/**
 * An implementation of [Interval] without information of pitches order.
 */
class UndirectedInterval(
    override val steps: Int,
) : Interval