package com.ch000se.messenger.feature.init.domain.entities

sealed class ShowKeyFeatureResult {
    data class Show(val keyFeature: KeyFeature) : ShowKeyFeatureResult()
    data object Skip : ShowKeyFeatureResult()
}