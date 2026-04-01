package com.ch000se.messenger.core.essentials

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T, R> Container<T>.map(transform: (T) -> R): Container<R> {
    return when (this) {
        is Container.Loading -> Container.Loading
        is Container.Success<T> -> {
            val mappedValue: R = transform(data)
            Container.Success(mappedValue, isLoadingInBackground)
        }
        is Container.Error -> Container.Error(exception)
    }
}

fun <T, R> Flow<Container<T>>.containerMap(transform: (T) -> R): Flow<Container<R>> {
    return map { container ->
        container.map(transform)
    }
}
