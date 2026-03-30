package com.ch000se.messenger.core.theme.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProgressButton(
    modifier: Modifier = Modifier,
    isInProgress: Boolean,
    text: String,
    onClick: () -> Unit,
) {
    Box(modifier = modifier) {
        Button(
            modifier = if (isInProgress) Modifier.alpha(0f) else Modifier,
            onClick = onClick,
        ) {
            Text(text = text)
        }
        if (isInProgress) {
            SmallProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}

@Preview
@Composable
private fun ProgressButtonPreviewWithoutProgress() {
    ProgressButton(
        isInProgress = false,
        text = "Button",
        onClick = {}
    )

}

@Preview
@Composable
private fun ProgressButtonPreviewWithProgress() {
    ProgressButton(
        isInProgress = true,
        text = "Button",
        onClick = {}
    )
}