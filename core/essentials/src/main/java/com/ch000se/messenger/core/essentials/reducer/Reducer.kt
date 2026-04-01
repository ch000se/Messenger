package com.ch000se.messenger.core.essentials.reducer

import kotlinx.coroutines.flow.StateFlow

interface Reducer<State> {
    val stateFlow: StateFlow<State>
    fun update(transform: suspend (State) -> State)
}
