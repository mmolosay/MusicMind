package io.github.mmolosay.musicmind.theory.scales

import io.github.mmolosay.musicmind.theory.instruments.DiscretePitchInstrument
import io.github.mmolosay.musicmind.theory.pitch.Pitch

/**
 * Finite scale represents a musical scale as materialized list of entries of type [T] (notes or pitches).
 *
 * Finite scale may be not full, if an instrument in which context it was materialized can't produce all notes of
 * scale's definition.
 *
 * This type of scale contains every entry of the scale __without__ an octave entry, which is an entry `(n + 1)`
 * in an `n-sized` scale and is a tonic but one octave higher.
 *
 * [Scale — Wikipedia](https://en.wikipedia.org/wiki/Scale_(music))
 */
@JvmInline
value class FiniteScale<out T>(val entries: List<T>)

internal val EmptyFiniteScale: FiniteScale<Nothing> = FiniteScale(listOf())

val <T> FiniteScale<T>.tonic: T
    get() = entries.first()

typealias FinitePitchScale = FiniteScale<Pitch>

typealias FiniteNoteScale = FiniteScale<DiscretePitchInstrument.Note>