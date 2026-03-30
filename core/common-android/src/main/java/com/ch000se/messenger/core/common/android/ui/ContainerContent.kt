package com.ch000se.messenger.core.common.android.ui

import androidx.compose.runtime.Composable
import com.ch000se.messenger.core.essentials.Container

@Composable
fun <T> ContainerContent(
    container: Container<T>,
    onLoading: @Composable () -> Unit = {},
    onError: @Composable (Exception) -> Unit = {},
    onSuccess: @Composable (T) -> Unit
) {
    when (container) {
        is Container.Loading -> onLoading()
        is Container.Error -> onError(container.exception)
        is Container.Success -> onSuccess(container.data)
    }
}
