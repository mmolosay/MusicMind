package io.github.mmolosay.musicmind.theory.pitch

interface PitchClassifier {

    fun List<Pitch>.classes(): List<PitchClass>
}