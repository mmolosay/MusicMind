package io.github.mmolosay.musicmind.theory.partition

/**
 * Distance is defined by the number of [steps] between two keys (positions) on a chromatic instrument.
 */
@JvmInline
value class Distance(val steps: Int)

fun List<Int>.asDistances(): List<Distance> =
    map { Distance(it) }