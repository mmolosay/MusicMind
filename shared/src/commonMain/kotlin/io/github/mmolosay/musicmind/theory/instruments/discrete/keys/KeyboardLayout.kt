package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

/**
 * Declares how keys of keyboard should be arranged.
 */
fun interface KeyboardLayout {

    /**
     * Constructs [Key.Rank] for a key at [keyOrdinal].
     */
    fun rankFor(keyOrdinal: Int): Key.Rank
}

@Suppress("FunctionName")
internal object KeyboardLayouts {

    fun For12PitchClasses(): KeyboardLayout =
        layoutBy(rankTiersFor12PitchClasses)

    fun For19PitchClasses(): KeyboardLayout =
        layoutBy(rankTiersFor19PitchClasses)

    private fun layoutBy(tiers: Array<Int>): KeyboardLayout =
        KeyboardLayout { ordinal ->
            Key.Rank(tier = tiers[(ordinal - 1) % tiers.size]) // tiers.size == pitch classes count == keys per octave
        }

    private val rankTiersFor12PitchClasses by lazy {
        arrayOf(0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0)
    }

    // http://www.n-ism.org/Projects/Microtonalism/keyboard_demo.php
    private val rankTiersFor19PitchClasses by lazy {
        arrayOf(0, 1, 2, 0, 1, 2, 0, 1, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1)
    }
}