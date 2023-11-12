package io.github.mmolosay.musicmind.presentation.resources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.intl.Locale
import io.github.mmolosay.musicmind.presentation.StringFamily
import io.github.mmolosay.musicmind.presentation.StringProvider
import io.github.mmolosay.musicmind.presentation.Strings
import io.github.mmolosay.musicmind.presentation.getOrEng

val LocalStringProvider = staticCompositionLocalOf<StringProvider> { DesktopStringProvider() }

@Composable
@ReadOnlyComposable
fun strings(type: Strings): StringFamily {
    val provider = LocalStringProvider.current
    return provider.get(type)
}

@Composable
@ReadOnlyComposable
fun localizedString(
    stringType: Strings,
    locale: Locale = Locale.current,
): String {
    val stringProvider = LocalStringProvider.current
    val stringFamily = stringProvider.get(stringType)
    return stringFamily.getOrEng(locale.language)
}