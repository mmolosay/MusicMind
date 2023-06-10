package io.github.mmolosay.musicmind.theory.label

import io.github.mmolosay.musicmind.theory.cents.Cents
import io.github.mmolosay.musicmind.theory.label.Label.Alteration.Sign
import io.github.mmolosay.musicmind.theory.label.Label.Natural

/**
 * Label of a pitch (note).
 * Denotes the position of a pitch in relation to neighboring [Natural] pitch.
 */
data class Label(
    val natural: Natural,
    val alteration: Alteration,
    val deviation: Cents,
) {

    /**
     * Seven natural notes.
     *
     * [Natural – Wikipedia](https://en.wikipedia.org/wiki/Natural_(music)#:~:text=Natural%20notes%20are%20the%20notes,natural%20pitch%20for%20each%20string.)
     */
    enum class Natural {
        A, B, C, D, E, F, G;
    }

    data class Alteration(
        val sign: Sign,
        val amount: Int,
    ) {

        enum class Sign {
            Sharp, Flat,
        }

        companion object {
            val None by lazy { 0.sharp }
        }
    }
}

fun Natural.label(
    alteration: Label.Alteration = Label.Alteration.None,
    deviation: Cents = Cents.Zero,
): Label =
    Label(this, alteration, deviation)

val Label.isAccurate: Boolean
    get() = (deviation == Cents.Zero)

val Int.sharp: Label.Alteration
    get() = Label.Alteration(sign = Sign.Sharp, amount = this)

val Int.flat: Label.Alteration
    get() = Label.Alteration(sign = Sign.Flat, amount = this)

val Label.Alteration.isZero: Boolean
    get() = (amount == 0)