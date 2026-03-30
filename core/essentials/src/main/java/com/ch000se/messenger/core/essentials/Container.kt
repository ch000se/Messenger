package com.ch000se.messenger.core.essentials

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed class Container<out T> {
    data object Loading : Container<Nothing>()
    data class Success<T>(val data: T) : Container<T>()
    data class Error(val exception: Exception) : Container<Nothing>()
}

fun <T, R> Container<T>.map(transform: (T) -> R): Container<R> {
    return when (this) {
        is Container.Loading -> Container.Loading
        is Container.Success<T> -> {
            val mappedValue: R = transform(data)
            Container.Success(mappedValue)
        }

        is Container.Error -> Container.Error(exception)
    }
}

fun <T, R> Flow<Container<T>>.containerMap(transform: (T) -> R): Flow<Container<R>> {
    return map { container ->
        container.map(transform)
    }
}