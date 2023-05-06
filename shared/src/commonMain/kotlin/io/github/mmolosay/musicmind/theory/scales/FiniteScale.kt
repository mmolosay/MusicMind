package io.github.mmolosay.musicmind.theory.scales

/**
 * Finite scale represents a musical scale as materialized list of entries of type [T] (notes or pitches).
 *
 * Finite scale may be not full, if an instrument in which context it was materialized can't produce all notes of
 * scale's definition.
 *
 * [Scale — Wikipedia](https://en.wikipedia.org/wiki/Scale_(music))
 */
sealed class FiniteScale<out T>(entries: List<T>) : List<T> by entries {

//    abstract
}

val <T> FiniteScale<T>.tonic: T
    get() = first()