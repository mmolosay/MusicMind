package io.github.mmolosay.musicmind.presentation

import io.github.mmolosay.musicmind.theory.label.Label
import io.github.mmolosay.musicmind.theory.label.Label.Alteration
import io.github.mmolosay.musicmind.theory.label.Label.Natural
import io.github.mmolosay.musicmind.theory.label.isAccurate

interface LabelFormatter {
    val Label.display: String
}

interface NaturalFormatter {
    val Natural.display: String
}

interface AlterationFormatter {
    val Alteration.display: String
}

class DefaultNoteFormatter : LabelFormatter, NaturalFormatter, AlterationFormatter {

    override val Label.display: String
        get() = natural.display + alteration.display + "*".takeUnless { isAccurate }.orEmpty()

    override val Natural.display: String
        get() = when (this) {
            Natural.A -> "A"
            Natural.B -> "B"
            Natural.C -> "C"
            Natural.D -> "D"
            Natural.E -> "E"
            Natural.F -> "F"
            Natural.G -> "G"
        }

    override val Alteration.display: String
        get() = when (sign) {
            Alteration.Sign.Sharp -> "♯"
            Alteration.Sign.Flat -> "♭"
        }
            .repeat(amount)
}