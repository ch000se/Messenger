package com.ch000se.messenger.feature.init.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch000se.messenger.core.essentials.Container
import com.ch000se.messenger.core.essentials.exceptions.handler.ExceptionHandler
import com.ch000se.messenger.core.essentials.map
import com.ch000se.messenger.feature.init.domain.IsAuthorizedUseCase
import com.ch000se.messenger.feature.init.domain.ShowKeyFeatureUseCase
import com.ch000se.messenger.feature.init.domain.entities.KeyFeature
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

interface GetKeyFeatureUseCase {
    suspend operator fun invoke(): KeyFeature
}

@HiltViewModel
class InitViewModel @Inject constructor(
    private val exceptionHandler: ExceptionHandler,
    private val router: InitRouter,
    private val showKeyFeatureUseCase: ShowKeyFeatureUseCase,
    private val isAuthorizedUseCase: IsAuthorizedUseCase
) : ViewModel() {

    private val vmStateFlow = MutableStateFlow(ViewModelState())

    val stateFlow: StateFlow<Container<State>> = combine(
        showKeyFeatureUseCase.invoke(),
        vmStateFlow
    ) { keyFeatureContainer, vmState ->
        keyFeatureContainer.map { keyFeature ->
            State(
                keyFeature = keyFeature,
                isCheckAuthInProgress = vmState.isCheckAuthInProgress
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), Container.Loading)


//    val stateFlow: StateFlow<Container<State>> = load {
//        val keyFeature = getKeyFeatureUseCase()
//        State(keyFeature)
//    }

    fun letsGo() {
        viewModelScope.launch {
            try {
                showProgress()
                val isAuthorized = isAuthorizedUseCase.invoke()
                if (isAuthorized) {

                } else {
                    router.launchSignIn()
                }
            } catch (e: Exception) {
                hideProgress()
                ensureActive()
                exceptionHandler.handleException(e)
            }
        }
    }

    private fun showProgress() {
        vmStateFlow.update { it.copy(isCheckAuthInProgress = true) }
    }

    private fun hideProgress() {
        vmStateFlow.update { it.copy(isCheckAuthInProgress = false) }
    }

    data class State(
        val keyFeature: KeyFeature,
        val isCheckAuthInProgress: Boolean
    )

    private data class ViewModelState(
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