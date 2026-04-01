package com.ch000se.messenger.core.essentials.reducer

import com.ch000se.messenger.core.essentials.Container
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking

interface ContainerReducer<State> : Reducer<Container<State>> {
    override val stateFlow: StateFlow<Container<State>>
    override fun update(transform: suspend (Container<State>) -> Container<State>)
    fun updateState(transform: suspend (State) -> State)
}

internal class ContainerReducerImpl<State>(
    private val mutableStateFlow: MutableStateFlow<Container<State>>,
    private val scope: CoroutineScope
) : ContainerReducer<State> {

    override val stateFlow: StateFlow<Container<State>> = mutableStateFlow

    override fun update(transform: suspend (Container<State>) -> Container<State>) {
        val currentValue = mutableStateFlow.value
        runBlocking {
            mutableStateFlow.value = transform(currentValue)
        }
    }

    override fun updateState(transform: suspend (State) -> State) {
        val container = mutableStateFlow.value
        if (container is Container.Success) {
            runBlocking {
                mutableStateFlow.value = Container.Success(
                    data = transform(container.data),
                    isLoadingInBackground = container.isLoadingInBackground
                )
            }
        }
    }
}
