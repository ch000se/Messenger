package com.ch000se.messenger.feature.signin.presentation

import androidx.lifecycle.ViewModel
import com.ch000se.messenger.core.essentials.Container
import com.ch000se.messenger.core.presentation.WithMviState
import com.ch000se.messenger.core.presentation.base.AbstractViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import com.ch000se.messenger.feature.signin.domain.GetSignInUseCase
import com.ch000se.messenger.feature.signin.domain.SaveSignInUseCase
import kotlinx.coroutines.delay
import java.util.UUID

@HiltViewModel
class SignInViewModel @Inject constructor(
    getSignInUseCase: GetSignInUseCase,
    private val saveSignInUseCase: SaveSignInUseCase,
) : ViewModel(),
    WithMviState<SignInViewModel.State> {

    private val reducer = getSignInUseCase()
        .containerToReducer(::State, State::copy)
    val stateFlow: StateFlow<Container<State>> = reducer.stateFlow

    fun increment() = launch {
        // example of updating data in domain:
        saveSignInUseCase("Random Title: ${UUID.randomUUID().toString()}")
        // example of local state update:
        delay(1000)
        reducer.updateState { oldState ->
            oldState.copy(counter = oldState.counter + 1)
        }
    }

    data class State(
        val title: String,
        val actionInProgress: Boolean = false,
        val counter: Int = 0,
    )

}
