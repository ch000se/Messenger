package com.ch000se.messenger.feature.init.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.ch000se.messenger.core.essentials.entities.ImageSource
import com.ch000se.messenger.core.navigation.dsl.ScreenScope
import com.ch000se.messenger.core.navigation.dsl.ScreenToolbar
import com.ch000se.messenger.core.navigation.dsl.viewModel
import com.ch000se.messenger.core.theme.Dimens
import com.ch000se.messenger.core.theme.components.ContainerView
import com.ch000se.messenger.core.theme.components.ImageView
import com.ch000se.messenger.core.theme.components.ProgressButton
import com.ch000se.messenger.core.theme.previews.PreviewScreenContent
import com.ch000se.messenger.core.theme.previews.ScreenPreview
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
    state: InitViewModel.State,
    onLetsGoAction: () -> Unit
) {
    val configuration = LocalConfiguration.current

    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        InitLandscapeContent(state, onLetsGoAction)
    } else {
        InitPortraitContent(state, onLetsGoAction)
    }
}

@Composable
fun InitPortraitContent(
    state: InitViewModel.State,
    onLetsGoAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.MediumPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.MediumSpace)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        val keyFeature = state.keyFeature
        Text(
            text = keyFeature.title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )

        ImageView(
            imageSource = keyFeature.image,
            modifier = Modifier.size(Dimens.LargeImageSize)
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
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun InitLandscapeContent(
    state: InitViewModel.State,
    onLetsGoAction: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val keyFeature = state.keyFeature

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(Dimens.MediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimens.MediumSpace)
        ) {
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
        ImageView(
            imageSource = keyFeature.image,
            modifier = Modifier
                .wrapContentSize()
                .size(Dimens.LargeImageSize)
                .weight(1f)
        )
    }
}

@ScreenPreview
@Composable
private fun InitContentPreview() = PreviewScreenContent {
    InitContent(
        state = InitViewModel.State(
            keyFeature = KeyFeature(
                id = 0,
                title = "Welcome to Messenger",
                description = "Your all-in-one messaging app for seamless communication.",
                image = ImageSource.Empty
            ),
            isCheckAuthInProgress = false
        ),
        onLetsGoAction = {}
    )
}