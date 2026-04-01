package com.ch000se.messenger.core.essentials.reducer

import com.ch000se.messenger.core.essentials.Container
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T1, T2, State> combineContainersToReducer(
    flow1: Flow<Container<T1>>,
    flow2: Flow<Container<T2>>,
    scope: CoroutineScope,
    started: SharingStarted,
    initialState: suspend (T1, T2) -> State,
    nextState: suspend (State, T1, T2) -> State = { _, t1, t2 -> initialState(t1, t2) }
): ContainerReducer<State> {
    val mutableStateFlow = MutableStateFlow<Container<State>>(Container.Loading)
    var currentState: State? = null

    combine(flow1, flow2) { c1, c2 ->
        when {
            c1 is Container.Loading || c2 is Container.Loading -> Container.Loading
            c1 is Container.Error -> Container.Error(c1.exception)
            c2 is Container.Error -> Container.Error(c2.exception)
            c1 is Container.Success && c2 is Container.Success -> {
                val isLoadingInBackground = c1.isLoadingInBackground || c2.isLoadingInBackground
                val state = if (currentState == null) {
                    initialState(c1.data, c2.data)
                } else {
                    nextState(currentState!!, c1.data, c2.data)
                }
                currentState = state
                Container.Success(state, isLoadingInBackground)
            }
            else -> Container.Loading
        }
    }.onEach { newContainer ->
        mutableStateFlow.value = newContainer
    }.launchIn(scope)

    return ContainerReducerImpl(mutableStateFlow, scope)
}

fun <T1, T2, T3, State> combineContainersToReducer(
    flow1: Flow<Container<T1>>,
    flow2: Flow<Container<T2>>,
    flow3: Flow<Container<T3>>,
    scope: CoroutineScope,
    started: SharingStarted,
    initialState: suspend (T1, T2, T3) -> State,
    nextState: suspend (State, T1, T2, T3) -> State = { _, t1, t2, t3 -> initialState(t1, t2, t3) }
): ContainerReducer<State> {
    val mutableStateFlow = MutableStateFlow<Container<State>>(Container.Loading)
    var currentState: State? = null

    combine(flow1, flow2, flow3) { c1, c2, c3 ->
        when {
            c1 is Container.Loading || c2 is Container.Loading || c3 is Container.Loading -> Container.Loading
            c1 is Container.Error -> Container.Error(c1.exception)
            c2 is Container.Error -> Container.Error(c2.exception)
            c3 is Container.Error -> Container.Error(c3.exception)
            c1 is Container.Success && c2 is Container.Success && c3 is Container.Success -> {
                val isLoadingInBackground = c1.isLoadingInBackground || c2.isLoadingInBackground || c3.isLoadingInBackground
                val state = if (currentState == null) {
                    initialState(c1.data, c2.data, c3.data)
                } else {
                    nextState(currentState!!, c1.data, c2.data, c3.data)
                }
                currentState = state
                Container.Success(state, isLoadingInBackground)
            }
            else -> Container.Loading
        }
    }.onEach { newContainer ->
        mutableStateFlow.value = newContainer
    }.launchIn(scope)

    return ContainerReducerImpl(mutableStateFlow, scope)
}
