package io.github.mmolosay.musicmind.theory.scales

import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.InstrumentKey

class FiniteKeyScale internal constructor(
    keys: List<InstrumentKey>,
) : FiniteScale<InstrumentKey>(keys)