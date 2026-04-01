package com.ch000se.messenger.core.presentation

import com.ch000se.messenger.core.essentials.Container
import com.ch000se.messenger.core.essentials.reducer.ContainerReducer
import com.ch000se.messenger.core.essentials.reducer.combineContainersToReducer
import com.ch000se.messenger.core.essentials.successContainer
import com.ch000se.messenger.core.presentation.base.ViewModelMixin
import com.ch000se.messenger.core.presentation.base.getMixinState
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

interface WithMviState<State> : ViewModelMixin, WithCommonDependencies {

    enum class HideProgressPolicy {
        OnFinally,
        OnError
    }

    val progressStateFlow: StateFlow<Boolean> get() = getMixinState().progressStateFlow

    fun <T> Flow<Container<T>>.containerToReducer(
        initialState: suspend (T, Boolean) -> State,
        nextState: suspend (State, T, Boolean) -> State = { _, t, isProgress -> initialState(t, isProgress) }
    ): ContainerReducer<State> {
        return combineContainersToReducer(
            flow1 = this,
            flow2 = progressStateFlow.map(::successContainer),
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000, replayExpirationMillis = 0),
            initialState = initialState,
            nextState = nextState
        )
    }
    fun launch(
        hideProgressPolicy: HideProgressPolicy = HideProgressPolicy.OnFinally,
        action: suspend () -> Unit
    ) {
        coroutineScope.launch {
            try {
                updateProgress(true)
                action()
            } catch (e: Exception) {
                ensureActive()
                if (hideProgressPolicy == HideProgressPolicy.OnError) updateProgress(false)
                exceptionHandler.handleException(e)
                logger.e(e)
            } finally {
                if (hideProgressPolicy == HideProgressPolicy.OnFinally) updateProgress(false)
            }
        }
    }

    private fun getMixinState() = getMixinState(::MixinState)
    private fun updateProgress(value: Boolean) = with(getMixinState()) {
        progressStateFlow.value = value
    }

    private class MixinState(
        val progressStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    )
}