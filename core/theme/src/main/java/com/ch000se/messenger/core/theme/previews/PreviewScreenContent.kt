package com.ch000se.messenger.core.theme.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ch000se.messenger.core.theme.material.MessengerTheme

@Composable
fun PreviewScreenContent(
    content: @Composable () -> Unit
) {
    MessengerTheme {
        Surface(modifier = Modifier.fillMaxSize()){
            content()
        }
    }
}