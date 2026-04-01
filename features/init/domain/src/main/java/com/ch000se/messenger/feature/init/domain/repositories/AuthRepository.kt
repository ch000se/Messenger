package com.ch000se.messenger.feature.init.domain.repositories


interface AuthRepository {
    suspend fun isAuthorized(): Boolean
}