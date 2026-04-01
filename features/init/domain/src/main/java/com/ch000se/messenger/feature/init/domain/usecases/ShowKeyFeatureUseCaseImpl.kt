package com.ch000se.messenger.feature.init.domain.usecases

import com.ch000se.messenger.feature.init.domain.ShowKeyFeatureUseCase
import com.ch000se.messenger.feature.init.domain.entities.KeyFeature
import com.ch000se.messenger.feature.init.domain.entities.ShowKeyFeatureResult
import com.ch000se.messenger.feature.init.domain.repositories.DateTimeRepository
import com.ch000se.messenger.feature.init.domain.repositories.KeyFeaturesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShowKeyFeatureUseCaseImpl @Inject constructor(
    private val keyFeaturesRepository: KeyFeaturesRepository,
    private val dateTimeRepository: DateTimeRepository
) : ShowKeyFeatureUseCase {

    override fun invoke(): Flow<ShowKeyFeatureResult> = flow {
        if (shouldShowKeyFeature()) {
            val keyFeature = getRandomKeyFeature()
            emit(ShowKeyFeatureResult.Show(keyFeature))
            saveDisplayTime(keyFeature)
        } else {
            emit(ShowKeyFeatureResult.Skip)
        }
    }

    private suspend fun shouldShowKeyFeature(): Boolean {
        val period = keyFeaturesRepository.getDisplayPeriod()
        val lastDisplayTime = keyFeaturesRepository.getKeyFeatures()
            .maxOf { it.lastDisplayTime }
        val now = dateTimeRepository.now()
        return lastDisplayTime + period < now
    }

    private suspend fun getRandomKeyFeature(): KeyFeature {
        val keyFeatures = keyFeaturesRepository.getKeyFeatures()
        return keyFeatures.minBy { it.lastDisplayTime }
    }

    private suspend fun saveDisplayTime(keyFeature: KeyFeature) {
        val now = dateTimeRepository.now()
        keyFeaturesRepository.saveDisplayTime(keyFeature, now)
    }
}