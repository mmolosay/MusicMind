package io.github.mmolosay.musicmind.presentation

import io.github.mmolosay.musicmind.theory.pitch.Label

interface NaturalsFormatter {
    val Label.Natural.label: String
}

class DefaultNaturalsFormatter : NaturalsFormatter {

    override val Label.Natural.label: String
        get() = when (this) {
            Label.Natural.A -> "A"
            Label.Natural.B -> "B"
            Label.Natural.C -> "C"
            Label.Natural.D -> "D"
            Label.Natural.E -> "E"
            Label.Natural.F -> "F"
            Label.Natural.G -> "G"
        }
}