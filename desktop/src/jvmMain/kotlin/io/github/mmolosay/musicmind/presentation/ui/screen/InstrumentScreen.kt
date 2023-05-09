package io.github.mmolosay.musicmind.presentation.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.mmolosay.musicmind.presentation.Strings
import io.github.mmolosay.musicmind.presentation.resources.localizedString
import io.github.mmolosay.musicmind.presentation.ui.Keyboard
import io.github.mmolosay.musicmind.presentation.ui.components.Screen

@Composable
fun InstrumentScreen() =
    Screen {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            title()
            keyboard()
        }
    }

private fun LazyListScope.title() {
    item(
        key = "title",
    ) {
        Text(
            text = localizedString(Strings.InstrumentsScreenTitle),
            style = MaterialTheme.typography.displaySmall,
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

private fun LazyListScope.keyboard() =
    item(
        key = "keyboard",
    ) {
        Keyboard(
            modifier = Modifier
                .height(400.dp),
            octaves = 2,
            onNaturalKeyClick = { println("White key!") },
            onAccidentalKeyClick = { println("Black key!") },
        )
    }