package io.github.mmolosay.musicmind.theory

/**
 * A ratio of two number.
 * Not specifically a music-relate interval ratio, but primarily used as such here.
 *
 * [Interval ratio – Wikipedia](https://en.wikipedia.org/wiki/Interval_ratio)
 */
data class Ratio(
    val antecedent: Int,
    val consequent: Int,
)

infix fun Int.to(consequent: Int): Ratio =
    Ratio(antecedent = this, consequent = consequent)

val Ratio.result: Float
    get() = (antecedent.toFloat() / consequent)