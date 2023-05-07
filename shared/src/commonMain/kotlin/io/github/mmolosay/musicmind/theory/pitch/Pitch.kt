package io.github.mmolosay.musicmind.theory.pitch

import io.github.mmolosay.musicmind.theory.Constants.OctaveRatio
import io.github.mmolosay.musicmind.theory.Constants.PitchEqualityThreshold
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.round

/**
 * Pitch is a note without a duration; a percievable frequency of sound.
 *
 * Though there's really no sense in creating pitches that are not percievable by human, you may do so.
 *
 * [Pitch](https://en.wikipedia.org/wiki/Pitch_(music))
 * [Tone](https://en.wikipedia.org/wiki/Musical_tone)
 */
@JvmInline
value class Pitch internal constructor(val frequency: Float) : Comparable<Pitch> {

    init {
        require(frequency > 0) { "Pitch can only be defined by positive frequency" }
    }

    override fun compareTo(other: Pitch): Int =
        frequency.compareTo(other.frequency)

    operator fun times(other: Int): Pitch =
        Pitch(frequency * other)

    operator fun times(other: Float): Pitch =
        Pitch(frequency * other)

    operator fun div(other: Pitch): Float =
        this.frequency / other.frequency

    operator fun div(other: Float): Pitch =
        Pitch(frequency / other)

    companion object {
        val HumanPercievableLowest by lazy { 20.hz }
        val HumanPercievableHighest by lazy { 20_000.hz }
        val InfinitelyLow by lazy { Pitch(Float.MIN_VALUE) }
        val InfinitelyHigh by lazy { Pitch(Float.MAX_VALUE) }
    }
}

val Float.hz: Pitch
    get() = Pitch(frequency = this)

val Double.hz: Pitch
    get() = Pitch(frequency = this.toFloat())

val Int.hz: Pitch
    get() = Pitch(frequency = this.toFloat())

val Pitch.octaveHigher: Pitch
    get() = this * OctaveRatio

val Pitch.isHumanPercievable: Boolean
    get() = this >= Pitch.HumanPercievableLowest && this <= Pitch.HumanPercievableHighest

fun Pitch.transpose(octaves: Int): Pitch =
    if (octaves == 0) this
    else this * OctaveRatio.pow(octaves)

infix fun Pitch.isPercievablyEqualTo(other: Pitch): Boolean =
    (this.frequency - other.frequency).absoluteValue < PitchEqualityThreshold

infix fun Pitch.isOfSamePitchClassAs(other: Pitch): Boolean {
    val ratio = other / this
    val log2ratio = ln(ratio) / Log2
    val diff = abs(round(log2ratio) - log2ratio)
    return (diff < PitchEqualityThreshold)
}

private val Log2 = ln(2.0)