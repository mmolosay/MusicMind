package io.github.mmolosay.musicmind.theory.tuning

import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.FretboardKeys
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.InstrumentKey
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.KeyboardKeys
import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.tuning.instrument.FretboardTuning
import io.github.mmolosay.musicmind.theory.tuning.instrument.KeyboardTuning
import io.github.mmolosay.musicmind.theory.tuning.system.EqualTemperament
import io.github.mmolosay.musicmind.theory.tuning.system.PureIntonation
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem

/**
 * Associates provided keys with pitches from [pitchSequencer].
 */
// TODO: just Tuner?
internal class KeysTuner(
    private val pitchSequencer: PitchSequencer,
) {

    fun KeyboardKeys.tune(
        tuningSystem: TuningSystem,
        keyboardTuning: KeyboardTuning,
    ): Map<InstrumentKey, Pitch> =
        when (tuningSystem) {
            is EqualTemperament -> tune(tuningSystem, keyboardTuning)
            is PureIntonation -> tune(tuningSystem, keyboardTuning)
        }

    fun FretboardKeys.tune(
        tuningSystem: TuningSystem,
        fretboardTuning: FretboardTuning,
    ): Map<InstrumentKey, Pitch> =
        when (tuningSystem) {
            is EqualTemperament -> tune(tuningSystem, fretboardTuning)
            is PureIntonation -> tune(tuningSystem, fretboardTuning)
        }

    private fun KeyboardKeys.tune(
        equalTemperament: EqualTemperament,
        keyboardTuning: KeyboardTuning,
    ): Map<InstrumentKey, Pitch> {
        val pitches = with(pitchSequencer) { equalTemperament + keyboardTuning }
            .take(list.size)
            .toList()
        return list.zip(pitches).toMap()
    }

    private fun KeyboardKeys.tune(
        pureIntonation: PureIntonation,
        keyboardTuning: KeyboardTuning,
    ): Map<InstrumentKey, Pitch> =
        TODO()

    fun FretboardKeys.tune(
        equalTemperament: EqualTemperament,
        fretboardTuning: FretboardTuning,
    ): Map<InstrumentKey, Pitch> {
        val keysPerString = strings.map { it.size }
        val sequences = with(pitchSequencer) { equalTemperament + fretboardTuning }
        val pitches = sequences
            .zip(keysPerString)
            .map { (pitches, keys) -> pitches.take(keys).toList() }
            .flatten()
        val keys = strings.flatten()
        return keys.zip(pitches).toMap()
    }

    fun FretboardKeys.tune(
        pureIntonation: PureIntonation,
        fretboardTuning: FretboardTuning,
    ): Map<InstrumentKey, Pitch> =
        TODO()
}