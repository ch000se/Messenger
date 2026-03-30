package com.ch000se.messenger.feature.init.domain

import com.ch000se.messenger.core.essentials.Container
import com.ch000se.messenger.feature.init.domain.entities.KeyFeature
import kotlinx.coroutines.flow.Flow

interface ShowKeyFeatureUseCase {
    operator fun invoke(): Flow<Container<KeyFeature>>
}
