package io.github.mmolosay.musicmind.theory.tuning.instrument

import java.math.BigDecimal

data class PianoTuning(
    val a4Frequency: BigDecimal,
) : InstrumentTuning {

    companion object {
        /** Ordinal of A4 key on an 88-key piano. */
        const val A4Ordinal = 49
    }
}