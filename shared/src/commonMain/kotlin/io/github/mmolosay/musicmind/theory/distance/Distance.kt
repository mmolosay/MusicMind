package io.github.mmolosay.musicmind.theory.distance

/**
 * Distance is defined by the number of [steps] between two keys (positions) on a chromatic instrument.
 */
@JvmInline
value class Distance(val steps: Int)