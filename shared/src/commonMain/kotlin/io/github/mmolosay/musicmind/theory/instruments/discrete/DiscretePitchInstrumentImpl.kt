package io.github.mmolosay.musicmind.theory.instruments.discrete

import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.InstrumentKey
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.InstrumentKeys
import io.github.mmolosay.musicmind.theory.partition.OctavePartition
import io.github.mmolosay.musicmind.theory.partition.verify
import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.pitch.PitchClassifier
import io.github.mmolosay.musicmind.theory.scales.FiniteKeyScale
import io.github.mmolosay.musicmind.theory.tuning.instrument.InstrumentTuning
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem

data class DiscretePitchInstrumentImpl<out Keys : InstrumentKeys<*>> internal constructor(
    override val keys: Keys,
    override val notes: Map<InstrumentKey, Pitch>,
    override val tuningSystem: TuningSystem,
    override val tuning: InstrumentTuning,
    val pitchClassifier: PitchClassifier,
) : AbstractDiscretePitchInstrument<Keys>(pitchClassifier) {

    override fun InstrumentKey.scale(partition: OctavePartition): FiniteKeyScale {
        assertExists()
        tuningSystem.verify(partition)
        val keys = mutableListOf(this)
        var lastKey = this
        // iterate over distances without last "looping" one
        for (i in 0 until partition.distribution.size - 1) {
            val key = lastKey + partition.distribution[i]
            if (key != null) {
                keys.add(key)
                lastKey = key
            } else {
                break
            }
        }
        return FiniteKeyScale(keys = keys)
    }
}