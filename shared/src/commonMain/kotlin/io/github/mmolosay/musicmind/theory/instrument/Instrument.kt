package io.github.mmolosay.musicmind.theory.instrument

import io.github.mmolosay.musicmind.theory.tuning.TuningSystem

interface Instrument {
    val tuning: TuningSystem
}