package com.ch000se.messenger.feature.init.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ch000se.messenger.core.navigation.dsl.ScreenScope
import com.ch000se.messenger.core.navigation.dsl.ScreenToolbar
import com.ch000se.messenger.core.navigation.dsl.viewModel
import com.ch000se.messenger.core.theme.Dimens
import com.ch000se.messenger.core.theme.components.ContainerView
import com.ch000se.messenger.core.theme.components.ProgressButton
import com.ch000se.messenger.feature.init.domain.entities.KeyFeature
import com.ch000se.templates.presentation.R

fun ScreenScope.initScreen() {
    val viewModel: InitViewModel = viewModel()
    toolBar = ScreenToolbar.Hidden
    content {
        val container by viewModel.stateFlow.collectAsState()
        ContainerView(
            container = container,
            onReload = {},
            modifier = Modifier.fillMaxSize()
        ) { state ->
            InitContent(
                state = state,
                onLetsGoAction = viewModel::letsGo
            )
        }
    }

}

@Composable
fun InitContent(
    modifier: Modifier = Modifier,
    state: InitViewModel.State,
    onLetsGoAction: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Dimens.MediumPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val keyFeature = state.keyFeature
        Text(
            text = keyFeature.title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )

        Text(
            text = keyFeature.description,
            textAlign = TextAlign.Center
        )

        ProgressButton(
            isInProgress = state.isCheckAuthInProgress,
            text = stringResource(R.string.get_started),
            onClick = onLetsGoAction
        )
    }
}

@Preview
@Composable
private fun InitContentPreview() {
    InitContent(
        state = InitViewModel.State(
            keyFeature = KeyFeature(
                id = 0,
                title = "Welcome to Messenger",
                description = "Your all-in-one messaging app for seamless communication."
            ),
            isCheckAuthInProgress = false
        ),
        onLetsGoAction = {}
    )
}