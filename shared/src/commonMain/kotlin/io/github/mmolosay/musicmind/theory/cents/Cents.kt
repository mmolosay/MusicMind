package io.github.mmolosay.musicmind.theory.cents

/**
 * [Wikipedia](https://en.wikipedia.org/wiki/Cent_(music))
 */
@JvmInline
value class Cents internal constructor(private val amount: Int) : Comparable<Cents> {

    operator fun plus(other: Cents): Cents =
        Cents(this.amount + other.amount)

    operator fun minus(other: Cents): Cents =
        Cents(this.amount - other.amount)

    override fun compareTo(other: Cents): Int =
        this.amount.compareTo(other.amount)

    companion object {
        val Zero: Cents = Cents(0)
    }
}