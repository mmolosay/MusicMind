package io.github.mmolosay.musicmind.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 24.dp),
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
    ) {
        Box(
            modifier = Modifier.padding(contentPadding),
        ) {
            content()
        }
    }
}