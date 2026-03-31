package com.ch000se.messenger.feature.signin.domain

interface SaveSignInUseCase {
    suspend operator fun invoke(title: String)
}
