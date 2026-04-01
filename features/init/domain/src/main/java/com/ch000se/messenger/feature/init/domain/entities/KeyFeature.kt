package com.ch000se.messenger.feature.init.domain.entities

import com.ch000se.messenger.core.essentials.entities.ImageSource
import java.time.ZonedDateTime

data class KeyFeature(
    val id: Long,
    val title: String,
    val description: String,
    val image: ImageSource,
    internal val lastDisplayTime: ZonedDateTime
)
