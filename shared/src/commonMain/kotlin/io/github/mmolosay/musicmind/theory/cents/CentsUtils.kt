package io.github.mmolosay.musicmind.theory.cents

val Int.cents: Cents
    get() = Cents(amount = this)