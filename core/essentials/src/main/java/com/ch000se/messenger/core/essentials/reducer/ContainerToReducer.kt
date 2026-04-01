package com.ch000se.messenger.core.essentials.reducer

import com.ch000se.messenger.core.essentials.Container
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

fun <T, State> Flow<Container<T>>.containerToReducer(
    scope: CoroutineScope,
    started: SharingStarted,
    initialState: suspend (T) -> State,
    nextState: suspend (State, T) -> State = { _, newValue -> initialState(newValue) }
): ContainerReducer<State> {
    val mutableStateFlow = MutableStateFlow<Container<State>>(Container.Loading)
    var currentState: State? = null

    this.map { container ->
        when (container) {
            is Container.Loading -> Container.Loading
            is Container.Error -> Container.Error(container.exception)
            is Container.Success -> {
                val state = if (currentState == null) {
                    initialState(container.data)
                } else {
                    nextState(currentState!!, container.data)
                }
                currentState = state
                Container.Success(state, container.isLoadingInBackground)
            }
        }
    }.onEach { newContainer ->
        mutableStateFlow.value = newContainer
    }.launchIn(scope)

    return ContainerReducerImpl(mutableStateFlow, scope)
}

fun <T> Flow<Container<T>>.containerToReducer(
    scope: CoroutineScope,
    started: SharingStarted
): ContainerReducer<T> = containerToReducer(
    scope = scope,
    started = started,
    initialState = { it }
)
