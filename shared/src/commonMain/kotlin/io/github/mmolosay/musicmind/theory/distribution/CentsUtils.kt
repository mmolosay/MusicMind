package io.github.mmolosay.musicmind.theory.distribution

val Int.cents: Cents
    get() = Cents(amount = this)