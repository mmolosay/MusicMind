package io.github.mmolosay.musicmind.presentation

import java.util.Locale

data class StringFamily(
    val default: String,
    val en: String? = null,
) {

    fun get(language: String = getDefaultLanguage()): String =
        getOrNull(language) ?: default

    fun getOrNull(language: String = getDefaultLanguage()): String? =
        when (language) {
            Locale.ENGLISH.language -> en
            else -> null
        }

    private fun getDefaultLanguage(): String =
        Locale.getDefault().language
}