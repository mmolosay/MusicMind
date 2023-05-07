package io.github.mmolosay.musicmind.theory.scales

import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.Key

class FiniteKeyScale internal constructor(
    keys: List<Key>,
) : FiniteScale<Key>(keys)