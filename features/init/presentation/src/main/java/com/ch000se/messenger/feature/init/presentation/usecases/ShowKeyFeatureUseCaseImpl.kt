package com.ch000se.messenger.feature.init.presentation.usecases

import com.ch000se.messenger.core.essentials.Container
import com.ch000se.messenger.feature.init.domain.ShowKeyFeatureUseCase
import com.ch000se.messenger.feature.init.domain.entities.KeyFeature
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShowKeyFeatureUseCaseImpl @Inject constructor() : ShowKeyFeatureUseCase {

    override fun invoke(): Flow<Container<KeyFeature>> = flow {
        emit(Container.Loading)
        // TODO: Replace with actual implementation
        emit(
            Container.Success(
                KeyFeature(
                    id = 1,
                    title = "Welcome",
                    description = "Welcome to the Messenger app"
                )
            )
        )
    }
}