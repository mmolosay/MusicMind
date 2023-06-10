package io.github.mmolosay.musicmind.theory.instruments

import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.InstrumentKey
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.InstrumentKeys
import io.github.mmolosay.musicmind.theory.label.Label
import io.github.mmolosay.musicmind.theory.partition.OctavePartition
import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.pitch.PitchClass
import io.github.mmolosay.musicmind.theory.scales.FiniteKeyScale
import io.github.mmolosay.musicmind.theory.scales.FinitePitchScale
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem

/**
 * Discrete pitch instruments are those that are made to produce pitches in discrete steps.
 * Examples of such instruments are piano, xylophone, giutars (not every tuning).
 *
 * [Chromatic scale, musical instruments – Wikipedia](https://en.wikipedia.org/wiki/Diatonic_and_chromatic#Musical_instruments)
 */
interface DiscretePitchInstrument<Key : InstrumentKey, Keys : InstrumentKeys<Key>> : Instrument {

    val keys: Keys
    val tuningSystem: TuningSystem
    val notes: Map<InstrumentKey, Pitch>
    val pitchClasses: List<PitchClass>

    val InstrumentKey.exists: Boolean
    val InstrumentKey.label: Label
    val InstrumentKey.pitch: Pitch

    infix fun InstrumentKey.scale(partition: OctavePartition): FiniteKeyScale
    fun FiniteKeyScale.pitches(): FinitePitchScale
}