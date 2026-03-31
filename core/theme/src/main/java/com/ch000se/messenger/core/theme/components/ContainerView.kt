package com.ch000se.messenger.core.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ch000se.messenger.core.essentials.Container
import com.ch000se.messenger.core.essentials.exceptions.mapper.ExceptionToMessageMapper
import com.ch000se.messenger.core.theme.Dimens
import com.ch000se.messenger.core.theme.MediumVerticalSpace
import com.ch000se.theme.R

@Composable
fun <T> ContainerView(
    modifier: Modifier = Modifier,
    container: Container<T>,
    onReload: (silently: Boolean) -> Unit,
    exceptionToMessageMapper: ExceptionToMessageMapper = ExceptionToMessageMapper,
    content: @Composable BoxAndMapperScope.(T) -> Unit = {},
) {
    Box(modifier = modifier) {
        when (container) {
            is Container.Error -> {
                val message = exceptionToMessageMapper.getLocalizedMessage(container.exception)
                ErrorContainerView(message = message, onReload = onReload)
            }

            Container.Loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            is Container.Success -> {
                val data = container.data
                val isLoadingInBackground = container.isLoadingInBackground
                val combinedScope = remember(this@Box, isLoadingInBackground, onReload) {
                    BoxAndMapperScopeImpl(
                        boxScope = this@Box,
                        isLoadingInBackground = isLoadingInBackground,
                        onReloadAction = onReload
                    )
                }
                combinedScope.content(data)
            }
        }
    }
}

@Composable
fun BoxScope.ErrorContainerView(
    message: String,
    onReload: (silently: Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .align(Alignment.Center)
            .padding(Dimens.MediumPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center
        )
        MediumVerticalSpace()
        Button(
            onClick = { onReload(false) }
        ) {
            Text(text = stringResource(R.string.try_again))
        }
    }
}

interface ContainerMapperScope {
    val isLoadingInBackground: Boolean
    fun reload(silently: Boolean = false)
}

interface BoxAndMapperScope : BoxScope, ContainerMapperScope

private class BoxAndMapperScopeImpl(
    boxScope: BoxScope,
    override val isLoadingInBackground: Boolean,
    private val onReloadAction: (silently: Boolean) -> Unit
) : BoxAndMapperScope, BoxScope by boxScope {

    override fun reload(silently: Boolean) {
        onReloadAction(silently)
    }
}

@Preview
@Composable
private fun SuccessContainerView() {
    ContainerView(
        modifier = Modifier,
        container = Container.Success(data = "Hello, World!"),
        onReload = {}
    ) { data ->
        Text(text = data)
    }
}

@Preview
@Composable
private fun LoadingContainerView() {
    ContainerView(
        modifier = Modifier,
        container = Container.Loading,
        onReload = {}
    )
}

@Preview
@Composable
private fun ErrorContainerView() {
    ContainerView(
        modifier = Modifier,
        container = Container.Error(Exception("Something went wrong")),
        onReload = {}
    )
}