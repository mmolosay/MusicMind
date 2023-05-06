package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

@JvmInline
value class KeyboardKeys internal constructor(
    val all: List<Key>,
) : Keys {

    override val total: Int
        get() = all.size
}