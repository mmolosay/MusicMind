package io.github.mmolosay.musicmind.theory.pitch

import io.github.mmolosay.musicmind.theory.Constants
import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.round

/**
 * Pitch is a note without a duration; a percievable frequency of sound.
 *
 * [Pitch](https://en.wikipedia.org/wiki/Pitch_(music))
 * [Tone](https://en.wikipedia.org/wiki/Musical_tone)
 */
@JvmInline
value class Pitch internal constructor(val frequency: Double) : Comparable<Pitch> {

    override fun compareTo(other: Pitch): Int =
        frequency.compareTo(other.frequency)

    operator fun times(other: Int): Pitch =
        Pitch(frequency * other)

    operator fun times(other: Double): Pitch =
        Pitch(frequency * other)

    operator fun div(other: Pitch): Double =
        this.frequency / other.frequency

    companion object {
        val InfinitelyLow by lazy { Pitch(Double.MIN_VALUE) }
        val InfinitelyHigh by lazy { Pitch(Double.MAX_VALUE) }
    }
}

val Double.hz: Pitch
    get() = Pitch(frequency = this)

val Int.hz: Pitch
    get() = Pitch(frequency = this.toDouble())

val Pitch.octaveHigher: Pitch
    get() = this * Constants.OctaveRatio

infix fun Pitch.isOfSamePitchClassAs(other: Pitch): Boolean {
    val ratio = other / this
    val log2ratio = ln(ratio) / Log2
    val diff = abs(round(log2ratio) - log2ratio)
    return (diff < 2e-5)
}

private val Log2 = ln(2.0)