package com.ch000se.messenger.feature.init.presentation.usecases

import com.ch000se.messenger.core.essentials.Container
import com.ch000se.messenger.core.essentials.entities.ImageSource
import com.ch000se.messenger.feature.init.domain.ShowKeyFeatureUseCase
import com.ch000se.messenger.feature.init.domain.entities.KeyFeature
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private val KeyFeatures = listOf(
    KeyFeature(
        id = 1L,
        title = "11111111",
        description = "1111111111111111111111111111111",
        image = ImageSource.Remote("")
    ),
    KeyFeature(
        id = 2L,
        title = "22222222",
        description = "22222222222222222222222222222222222",
        image = ImageSource.Remote("")
    ),
    KeyFeature(
        id = 3L,
        title = "3333333",
        description = "333333333333333333333333333333333333",
        image = ImageSource.Remote("")
    )
)

class ShowKeyFeatureUseCaseImpl @Inject constructor() : ShowKeyFeatureUseCase {

    override fun invoke(): Flow<Container<KeyFeature>> = flow {
        emit(Container.Loading)
        // TODO: Replace with actual implementation
        emit(
            Container.Success(KeyFeatures.random())
        )
    }
}