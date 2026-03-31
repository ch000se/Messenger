package com.ch000se.messenger.feature.signin.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.ch000se.messenger.core.essentials.Container
import com.ch000se.messenger.core.navigation.dsl.ScreenScope
import com.ch000se.messenger.core.navigation.dsl.toolbar.toolbar
import com.ch000se.messenger.core.navigation.dsl.viewModel
import com.ch000se.messenger.core.theme.components.ContainerView
import com.ch000se.messenger.core.theme.components.ProgressButton
import com.ch000se.messenger.core.theme.previews.PreviewScreenContent
import com.ch000se.messenger.core.theme.previews.ScreenPreview
import com.ch000se.messenger.feature.signin.presentation.SignInViewModel.State

fun ScreenScope.signInScreen() {
    val viewModel: SignInViewModel = viewModel()
    toolbar {
        titleRes = R.string.sign_in_title
    }
    content {
        val container: Container<State> by viewModel.stateFlow.collectAsState()
        ContainerView(
            container = container,
            modifier = Modifier.fillMaxSize(),
        ) { state ->
            SignInContent(
                state = state,
                onIncrement = viewModel::increment,
            )
        }
    }
}

@Composable
fun BoxScope.SignInContent(
    state: State,
    onIncrement: () -> Unit = {},
) {
    Column(
        modifier = Modifier.align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "${state.title}",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = "Counter: ${state.counter}",
            style = MaterialTheme.typography.headlineLarge,
        )
        ProgressButton(
            isInProgress = state.actionInProgress,
            text = "Increment",
            onClick = onIncrement,
        )
    }

}

@ScreenPreview
@Composable
private fun SignInContentPreview() = PreviewScreenContent {
    Box(Modifier.fillMaxSize()) {
        SignInContent(State("SignIn Screen Preview"))
    }
}
