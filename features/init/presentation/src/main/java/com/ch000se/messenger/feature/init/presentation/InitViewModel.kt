package com.ch000se.messenger.feature.init.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch000se.messenger.core.essentials.Container
import com.ch000se.messenger.core.essentials.containerMap
import com.ch000se.messenger.core.essentials.exceptions.mapper.ExceptionToMessageMapper
import com.ch000se.messenger.feature.init.domain.entities.KeyFeature
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

interface GetKeyFeatureUseCase {
    suspend operator fun invoke(): KeyFeature
}

interface GetKeyFeatureUseCase1 {
    operator fun invoke(): Flow<Container<KeyFeature>>
}

@HiltViewModel
class InitViewModel @Inject constructor(
    private val exceptionToMessageMapper: ExceptionToMessageMapper,
    private val getKeyFeatureUseCase: GetKeyFeatureUseCase,
    private val getKeyFeatureUseCase1: GetKeyFeatureUseCase1
) : ViewModel() {

    val stateFlow: StateFlow<Container<State>> = getKeyFeatureUseCase1
        .invoke()
        .containerMap { keyFeature -> State(keyFeature) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), Container.Loading)


//    val stateFlow: StateFlow<Container<State>> = load {
//        val keyFeature = getKeyFeatureUseCase()
//        State(keyFeature)
//    }

    data class State(
        val keyFeature: KeyFeature,
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