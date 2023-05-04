package io.github.mmolosay.musicmind.theory.instruments

import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.pitch.PitchClass
import io.github.mmolosay.musicmind.theory.tuning.TuningSystem

interface Instrument {
    val tuning: TuningSystem
    val pitches: List<Pitch>
    val pithClasses: List<PitchClass>

    val Key.pitch: Pitch
}