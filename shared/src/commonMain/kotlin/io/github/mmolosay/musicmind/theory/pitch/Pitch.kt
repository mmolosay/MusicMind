package io.github.mmolosay.musicmind.theory.pitch

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

    companion object {
        val InfinitelyLow by lazy { Pitch(Double.MIN_VALUE) }
        val InfinitelyHigh by lazy { Pitch(Double.MAX_VALUE) }
    }
}

val Double.hz: Pitch
    get() = Pitch(frequency = this)

val Int.hz: Pitch
    get() = Pitch(frequency = this.toDouble())