package io.github.mmolosay.musicmind.theory.tuning.instrument

/**
 * Tuning for a discrete pitch instrument with a keyboard.
 * Frequency of [a4Frequency] reference pitch should be given with precision of
 * [io.github.mmolosay.musicmind.theory.Constants.PitchEqualityThreshold].
 */
data class KeyboardTuning(
    val a4Frequency: Float,
) : InstrumentTuning {

    companion object {
        /** Ordinal of A4 key on an 88-key piano. */
        const val A4Ordinal = 49
    }
}