package io.github.mmolosay.musicmind.theory.pitch

data class PitchLabel(
    val natural: Natural,
    val alteration: Alteration,
    val alterationAmount: Int,
    val isAccurate: Boolean,
) {

    enum class Natural {
        A, B, C, D, E, F, G,
    }

    enum class Alteration {
        Sharp, Flat,
    }
}