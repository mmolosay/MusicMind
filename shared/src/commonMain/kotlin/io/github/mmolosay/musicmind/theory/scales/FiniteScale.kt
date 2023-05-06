package io.github.mmolosay.musicmind.theory.scales

import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.Key
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
class FiniteScale<out T>(entries: List<T>) : List<T> by entries

internal val EmptyFiniteScale: FiniteScale<Nothing> = FiniteScale(emptyList())

val <T> FiniteScale<T>.tonic: T
    get() = first()

typealias FinitePitchScale = FiniteScale<Pitch>

typealias FiniteKeyScale = FiniteScale<Key>