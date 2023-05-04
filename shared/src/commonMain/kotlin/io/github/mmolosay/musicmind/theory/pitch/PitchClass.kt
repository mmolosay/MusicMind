package io.github.mmolosay.musicmind.theory.pitch

/**
 * @param [ordinal] an ordinal of this pitch class in an octave.
 *
 * [Pitch class – Wikipedia](https://en.wikipedia.org/wiki/Pitch_class)
 */
@JvmInline
value class PitchClass(val pitches: List<Pitch>)