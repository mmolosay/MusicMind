package io.github.mmolosay.musicmind.theory.instruments.discrete

import io.github.mmolosay.musicmind.theory.instruments.DiscretePitchInstrument
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.InstrumentKey
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.InstrumentKeys
import io.github.mmolosay.musicmind.theory.partition.Distance
import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.pitch.PitchClass
import io.github.mmolosay.musicmind.theory.pitch.PitchClassifier
import io.github.mmolosay.musicmind.theory.pitch.Label
import io.github.mmolosay.musicmind.theory.pitch.PitchLabels
import io.github.mmolosay.musicmind.theory.scales.FiniteKeyScale
import io.github.mmolosay.musicmind.theory.scales.FinitePitchScale

/**
 * Abstract implementation of [DiscretePitchInstrument] that defines some of its fields and methods.
 * Use this component in order to create custom implementation of [DiscretePitchInstrument].
 */
abstract class AbstractDiscretePitchInstrument<out Keys : InstrumentKeys<*>> : DiscretePitchInstrument<Keys> {

    protected abstract val pitchClassifier: PitchClassifier

    override val range: ClosedRange<Pitch> by lazy {
        notes.values.min()..notes.values.max()
    }

    override val Pitch.exists: Boolean
        get() = notes.containsValue(this)

    override val pitchClasses: List<PitchClass> by lazy {
        with(pitchClassifier) { notes.values.toList().classes() }
    }

    override val InstrumentKey.exists: Boolean
        get() = ordinal <= keys.total

    override val InstrumentKey.label: Label
        get() = with(PitchLabels) { tuningSystem label notes.getValue(this@label) }

    override val InstrumentKey.pitch: Pitch
        get() = notes.getValue(this)

    override fun FiniteKeyScale.pitches(): FinitePitchScale =
        FinitePitchScale(map { it.pitch })

    protected fun InstrumentKey.assertExists() {
        require(exists) { "Key $this does not exist in this instrument" }
    }

    protected operator fun InstrumentKey.plus(distance: Distance): InstrumentKey? {
        val resultOrdinal = this.ordinal + distance.steps
        return keys.with(resultOrdinal)
    }
}