package io.github.mmolosay.musicmind.theory.tuning

import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.FretboardKeys
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.Key
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
    ): Map<Key, Pitch> =
        when (tuningSystem) {
            is EqualTemperament -> tune(tuningSystem, keyboardTuning)
            is PureIntonation -> tune(tuningSystem, keyboardTuning)
        }

    fun FretboardKeys.tune(
        tuningSystem: TuningSystem,
        fretboardTuning: FretboardTuning,
    ): Map<Key, Pitch> =
        when (tuningSystem) {
            is EqualTemperament -> tune(tuningSystem, fretboardTuning)
            is PureIntonation -> tune(tuningSystem, fretboardTuning)
        }

    private fun KeyboardKeys.tune(
        equalTemperament: EqualTemperament,
        keyboardTuning: KeyboardTuning,
    ): Map<Key, Pitch> =
        with(pitchSequencer) { equalTemperament + keyboardTuning }
            .let { pitches ->
                all.mapIndexed { i, key -> key to pitches.elementAt(i) }.toMap()
            }

    private fun KeyboardKeys.tune(
        pureIntonation: PureIntonation,
        keyboardTuning: KeyboardTuning,
    ): Map<Key, Pitch> =
        TODO()

    fun FretboardKeys.tune(
        equalTemperament: EqualTemperament,
        fretboardTuning: FretboardTuning,
    ): Map<Key, Pitch> {
        val sequences = with(pitchSequencer) { equalTemperament + fretboardTuning }
        return strings
            .map { set -> set.flatten() }.zip(sequences)
            .flatMap { (keys, sequence) -> keys.zip(sequence.take(keys.size).toList()) }
            .toMap()
    }

    fun FretboardKeys.tune(
        pureIntonation: PureIntonation,
        fretboardTuning: FretboardTuning,
    ): Map<Key, Pitch> =
        TODO()
}