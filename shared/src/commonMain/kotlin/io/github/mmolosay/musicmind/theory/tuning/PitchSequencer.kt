package io.github.mmolosay.musicmind.theory.tuning

import io.github.mmolosay.musicmind.theory.tuning.instrument.InstrumentTuning
import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem

/**
 * A pitch sequencer is a component that can combine [TuningSystem] and an [InstrumentTuning] to produce a
 * [Sequence] of [Pitch]es.
 *
 * Has nothing in common with musical sequencer.
 */
interface PitchSequencer {

    operator fun TuningSystem.plus(tuning: InstrumentTuning): Sequence<Pitch>
}