package com.ch000se.messenger.feature.signin.presentation

import androidx.lifecycle.ViewModel
import com.ch000se.messenger.core.essentials.Container
import com.ch000se.messenger.feature.signin.domain.GetSignInUseCase
import com.ch000se.messenger.feature.signin.domain.SaveSignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    getSignInUseCase: GetSignInUseCase,
    private val saveSignInUseCase: SaveSignInUseCase,
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<Container<State>>(
        Container.Success(State(title = "Sign In"))
    )
    val stateFlow: StateFlow<Container<State>> = _stateFlow

    fun increment() {
        val currentState = (_stateFlow.value as? Container.Success)?.data ?: return
        _stateFlow.value = Container.Success(currentState.copy(counter = currentState.counter + 1))
    }

    data class State(
        val title: String,
        val actionInProgress: Boolean = false,
        val counter: Int = 0,
    )

}
