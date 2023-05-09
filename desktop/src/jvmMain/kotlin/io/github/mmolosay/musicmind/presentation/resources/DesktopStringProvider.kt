package io.github.mmolosay.musicmind.presentation.resources

import io.github.mmolosay.musicmind.presentation.StringFamily
import io.github.mmolosay.musicmind.presentation.StringProvider
import io.github.mmolosay.musicmind.presentation.Strings

class DesktopStringProvider : StringProvider {

    override fun get(type: Strings): StringFamily =
        strings.getValue(type)

    private companion object {

        val InstrumentScreenTitle = StringFamily(
            default = "Instruments",
        )

        val strings = mapOf(
            Strings.InstrumentsScreenTitle to InstrumentScreenTitle,
        )
    }
}