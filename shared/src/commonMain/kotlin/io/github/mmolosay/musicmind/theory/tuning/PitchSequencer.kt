package io.github.mmolosay.musicmind.theory.tuning

import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.tuning.instrument.FretboardTuning
import io.github.mmolosay.musicmind.theory.tuning.instrument.InstrumentTuning
import io.github.mmolosay.musicmind.theory.tuning.instrument.KeyboardTuning
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem

/**
 * A pitch sequencer is a component that can combine [TuningSystem] and an [InstrumentTuning] to produce
 * one or multiple [Sequence] of [Pitch]es.
 *
 * Has nothing to do with a regular musical sequencer.
 */
interface PitchSequencer {
    operator fun TuningSystem.plus(tuning: KeyboardTuning): Sequence<Pitch>
    operator fun TuningSystem.plus(tuning: FretboardTuning): List<Sequence<Pitch>>
}