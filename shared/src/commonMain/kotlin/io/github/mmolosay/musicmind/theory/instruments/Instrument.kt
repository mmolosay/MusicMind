package io.github.mmolosay.musicmind.theory.instruments

import io.github.mmolosay.musicmind.theory.perception.Pitch
import io.github.mmolosay.musicmind.theory.tuning.TuningSystem

interface Instrument {
    val tuning: TuningSystem
    val pitches: List<Pitch>

    val Key.pitch: Pitch
}