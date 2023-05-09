package io.github.mmolosay.musicmind.presentation

interface StringProvider {
    fun get(type: Strings): StringFamily
}