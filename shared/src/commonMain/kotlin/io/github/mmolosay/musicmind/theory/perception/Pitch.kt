package io.github.mmolosay.musicmind.theory.perception

/**
 * Pitch is a note without a duration; a percievable frequency of sound.
 *
 * [Pitch](https://en.wikipedia.org/wiki/Pitch_(music))
 * [Tone](https://en.wikipedia.org/wiki/Musical_tone)
 */
@JvmInline
value class Pitch(val frequency: Double)