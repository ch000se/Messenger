package com.ch000se.messenger.feature.init.domain

import com.ch000se.messenger.feature.init.domain.entities.ShowKeyFeatureResult
import kotlinx.coroutines.flow.Flow

interface ShowKeyFeatureUseCase {
    operator fun invoke(): Flow<ShowKeyFeatureResult>

}
