package io.github.mmolosay.musicmind.theory.scales

import io.github.mmolosay.musicmind.theory.perception.Pitch

/**
 * A scale is any set of musical notes ordered by fundamental frequency or pitch.
 *
 * [Scale – Wikipedia](https://en.wikipedia.org/wiki/Scale_(music))
 */
class Scale(
    val notes: List<Pitch>,
)

val Scale.size: Int
    get() = notes.size

