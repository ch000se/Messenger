package com.ch000se.messenger.feature.signin.domain.repositories


import com.ch000se.messenger.core.essentials.Container
import kotlinx.coroutines.flow.Flow

interface SignInRepository {
    fun getFeatureName(): Flow<Container<String>>
    suspend fun setFeatureName(newName: String)
}
