package io.github.mmolosay.musicmind.theory.instruments.continuous

import io.github.mmolosay.musicmind.theory.instruments.ContinuousPitchInstrument
import io.github.mmolosay.musicmind.theory.pitch.Pitch

/**
 * Abstract implementation of [ContinuousPitchInstrument] that defines some of its fields and methods.
 * Use this component in order to create custom implementation of [ContinuousPitchInstrument].
 */
abstract class AbstractContinuousPitchInstrument : ContinuousPitchInstrument {

    override val range: ClosedRange<Pitch> by lazy {
        TODO() // depends on tuning
    }

    override val Pitch.exists: Boolean
        get() = this in range
}