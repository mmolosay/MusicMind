package io.github.mmolosay.musicmind.theory.scales

import io.github.mmolosay.musicmind.theory.distance.Distance
import io.github.mmolosay.musicmind.theory.pitch.Pitch

/**
 * An implementation of scale with a [tonic] note, represented by an interval from
 * the first note of instrument.
 *
 * [Scale – Wikipedia](https://en.wikipedia.org/wiki/Scale_(music))
 */
data class Scale(
    val tonic: Pitch,
    override val intervals: List<Distance>,
) : AtonalScale