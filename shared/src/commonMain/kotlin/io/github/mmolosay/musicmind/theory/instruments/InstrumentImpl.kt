package io.github.mmolosay.musicmind.theory.instruments

import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.pitch.PitchClass
import io.github.mmolosay.musicmind.theory.pitch.PitchClasses.classes
import io.github.mmolosay.musicmind.theory.tuning.Tuner
import io.github.mmolosay.musicmind.theory.tuning.TuningSystem

internal class InstrumentImpl(
    override val tuning: TuningSystem,
    private val keys: Int,
) : Instrument {

    override val pitches: List<Pitch> by lazy {
        Tuner.tune(keys, tuning)
    }

    override val pithClasses: List<PitchClass> by lazy {
        pitches.classes()
    }

    override val Key.pitch: Pitch
        get() = pitches[ordinal]
}