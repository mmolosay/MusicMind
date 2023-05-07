package io.github.mmolosay.musicmind.theory.scales

import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.pitch.isOfSamePitchClassAs
import io.github.mmolosay.musicmind.theory.pitch.octaveHigher

class FinitePitchScale internal constructor(
    pitches: List<Pitch>,
) : FiniteScale<Pitch>(pitches)

// TODO: for any FiniteScale
val FinitePitchScale.hasOctaveEntry: Boolean
    get() = if (size > 1) first() isOfSamePitchClassAs last() else false

fun FinitePitchScale.withOctaveEntry(): FinitePitchScale =
    if (hasOctaveEntry) this
    else FinitePitchScale(this + first().octaveHigher)