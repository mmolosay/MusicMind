package io.github.mmolosay.musicmind.theory

/**
 * A ratio of two number.
 * Not specifically a music-relate interval ratio, but primarily used as such here.
 *
 * [Wikipedia](https://en.wikipedia.org/wiki/Interval_ratio)
 */
data class Ratio(
    val antecedent: Int,
    val consequent: Int,
)

//object UndefinedRatio : Ratio {
//    override val antecedent = error("")
//    override val consequent = error("")
//}

infix fun Int.to(consequent: Int): Ratio =
    Ratio(antecedent = this, consequent = consequent)

//val Ratio<*>.isUndefined: Boolean
//    get() = this is UndefinedRatio

val Ratio.result: Float
    get() = (antecedent.toFloat() / consequent)