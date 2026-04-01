package com.ch000se.messenger.feature.init.domain.repositories

import com.ch000se.messenger.feature.init.domain.entities.KeyFeature
import java.time.Period
import java.time.ZonedDateTime

interface KeyFeaturesRepository {
    suspend fun getKeyFeatures(): List<KeyFeature>

    suspend fun getDisplayPeriod(): Period
    suspend fun saveDisplayTime(keyFeature: KeyFeature, time: ZonedDateTime)
}