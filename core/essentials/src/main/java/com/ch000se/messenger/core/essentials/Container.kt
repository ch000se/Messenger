package com.ch000se.messenger.core.essentials

sealed class Container<out T> {
    data object Loading : Container<Nothing>()
    data class Success<T>(
        val data: T,
        val isLoadingInBackground: Boolean = false
    ) : Container<T>()

    data class Error(val exception: Exception) : Container<Nothing>()
}

fun <T> successContainer(data: T, isLoadingInBackground: Boolean = false): Container<T> =
    Container.Success(data, isLoadingInBackground)
