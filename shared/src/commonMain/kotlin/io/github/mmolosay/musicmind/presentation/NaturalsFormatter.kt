package io.github.mmolosay.musicmind.presentation

import io.github.mmolosay.musicmind.theory.pitch.PitchLabel

interface NaturalsFormatter {
    val PitchLabel.Natural.label: String
}

class DefaultNaturalsFormatter : NaturalsFormatter {

    override val PitchLabel.Natural.label: String
        get() = when (this) {
            PitchLabel.Natural.A -> "A"
            PitchLabel.Natural.B -> "B"
            PitchLabel.Natural.C -> "C"
            PitchLabel.Natural.D -> "D"
            PitchLabel.Natural.E -> "E"
            PitchLabel.Natural.F -> "F"
            PitchLabel.Natural.G -> "G"
        }
}