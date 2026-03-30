package com.ch000se.messenger.feature.init.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ch000se.messenger.core.common.android.ui.ContainerContent

@Composable
fun InitScreen(
    viewModel: InitViewModel = hiltViewModel()
) {
    val container by viewModel.stateFlow.collectAsState()

    ContainerContent(
        container = container,
        onLoading = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
        onError = { exception ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = exception.message ?: "Unknown error",
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        onSuccess = { state ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.keyFeature.title,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = state.keyFeature.description,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    )
}
