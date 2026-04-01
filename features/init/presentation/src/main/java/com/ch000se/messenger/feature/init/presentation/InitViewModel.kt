package com.ch000se.messenger.feature.init.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch000se.messenger.core.essentials.Container
import com.ch000se.messenger.core.presentation.WithInitCallback
import com.ch000se.messenger.core.presentation.WithMviState
import com.ch000se.messenger.core.presentation.base.AbstractViewModel
import com.ch000se.messenger.feature.init.domain.IsAuthorizedUseCase
import com.ch000se.messenger.feature.init.domain.ShowKeyFeatureUseCase
import com.ch000se.messenger.feature.init.domain.entities.KeyFeature
import com.ch000se.messenger.feature.init.domain.entities.ShowKeyFeatureResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class InitViewModel @Inject constructor(
    private val router: InitRouter,
    private val showKeyFeatureUseCase: ShowKeyFeatureUseCase,
    private val isAuthorizedUseCase: IsAuthorizedUseCase
) : AbstractViewModel(), WithInitCallback,
    WithMviState<InitViewModel.State> {

    private val keyFeatureFlow: Flow<Container<KeyFeature>> = flow {
        showKeyFeatureUseCase().collect { result ->
            when (result) {
                is ShowKeyFeatureResult.Show -> emit(Container.Success(result.keyFeature))
                ShowKeyFeatureResult.Skip -> authorize()
            }
        }
    }

    private val reducer = keyFeatureFlow
        .containerToReducer(
            initialState = ::State,
        )

    val stateFlow = reducer.stateFlow

//    val stateFlow: StateFlow<Container<State>> = combine(
//        keyFeatureFlow,
//        vmStateFlow
//    ) { keyFeatureContainer, vmState ->
//        keyFeatureContainer.map { keyFeature ->
//            State(
//                keyFeature = keyFeature,
//                isCheckAuthInProgress = vmState.isCheckAuthInProgress
//            )
//        }
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), Container.Loading)


//    val stateFlow: StateFlow<Container<State>> = load {
//        val keyFeature = getKeyFeatureUseCase()
//        State(keyFeature)
//    }

    fun letsGo() = launch(
        hideProgressPolicy = WithMviState.HideProgressPolicy.OnError,
        action = ::authorize
    )

    private suspend fun authorize() {
        val isAuthorized = isAuthorizedUseCase()
        if (isAuthorized) {

        } else {
            router.launchSignIn()
        }
    }


    override suspend fun onInitialized() {
        reducer.updateState { oldState ->
            oldState.copy()
        }
        TODO("Not yet implemented")
    }

    data class State(
        val keyFeature: KeyFeature,
        val isCheckAuthInProgress: Boolean = false
    )

}

fun <T> ViewModel.load(
    loader: suspend () -> T
): StateFlow<Container<T>> = flow {
    emit(Container.Loading)
    try {
        val result = loader()
        emit(Container.Success(result))
    } catch (e: Exception) {
        emit(Container.Error(e))
    }
}.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), Container.Loading)