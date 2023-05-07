package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

class KeyboardKeys internal constructor(
    keys: List<Key>,
) : Keys, List<Key> by keys {

    override val total: Int
        get() = size
}