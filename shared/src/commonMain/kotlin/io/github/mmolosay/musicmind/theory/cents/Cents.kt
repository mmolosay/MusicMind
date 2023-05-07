package io.github.mmolosay.musicmind.theory.cents

/**
 * Cent is a 1/100th of a semitone in 12-TET.
 *
 * It does not change its "size" in different tuning systems, and octave will always consist of 1200 cents in any.
 * However, since different tuning systems divide an octave in different ways (number of parts and its sizes),
 * the smallest interval (semitone in 12-TET) will be different, thus it will take different amount of cents to fill it.
 *
 * As said earlier, in a 12-TET there are 100 cents in a semitone.
 * It would be 38.7109... cents in 31-TET to fill its smallest interval.
 *
 * [Wikipedia](https://en.wikipedia.org/wiki/Cent_(music))
 */
@JvmInline
value class Cents internal constructor(private val amount: Float) : Comparable<Cents> {

    operator fun plus(other: Cents): Cents =
        Cents(this.amount + other.amount)

    operator fun minus(other: Cents): Cents =
        Cents(this.amount - other.amount)

    operator fun times(other: Float): Cents =
        Cents(this.amount * other)

    operator fun div(other: Int): Cents =
        Cents(this.amount / other)

    override fun compareTo(other: Cents): Int =
        this.amount.compareTo(other.amount)

    companion object {
        val Zero by lazy { Cents(0f) }
        val Octave by lazy { Cents(1200f) }
    }
}