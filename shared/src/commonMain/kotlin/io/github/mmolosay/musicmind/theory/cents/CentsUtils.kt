package io.github.mmolosay.musicmind.theory.cents

val Float.cents: Cents
    get() = Cents(amount = this)