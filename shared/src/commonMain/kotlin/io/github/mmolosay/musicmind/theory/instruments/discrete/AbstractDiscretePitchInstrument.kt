package io.github.mmolosay.musicmind.theory.instruments.discrete

import io.github.mmolosay.musicmind.theory.instruments.DiscretePitchInstrument
import io.github.mmolosay.musicmind.theory.instruments.DiscretePitchInstrument.Key
import io.github.mmolosay.musicmind.theory.instruments.DiscretePitchInstrument.Note
import io.github.mmolosay.musicmind.theory.partition.Distance
import io.github.mmolosay.musicmind.theory.pitch.Pitch

/**
 * Abstract implementation of [DiscretePitchInstrument] that defines some of its fields and methods.
 * Use this component in order to create custom implementation of [DiscretePitchInstrument].
 */
abstract class AbstractDiscretePitchInstrument : DiscretePitchInstrument {

    override val range: ClosedRange<Pitch> by lazy {
        notes.first().pitch..notes.last().pitch
    }

    override val Pitch.exists: Boolean
        get() = notes.any { it.pitch == this }

    override val Note.exists: Boolean
        get() = notes.contains(this)

    override fun List<Note>.atKey(predicate: (Key) -> Boolean): Note =
        first { predicate(it.key) }

    protected operator fun Note.plus(distance: Distance): Note? =
        notes.getOrNull(notes.indexOf(this) + distance.steps)
}