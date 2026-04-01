package com.ch000se.messenger.feature.init.domain.usecases

import com.ch000se.messenger.feature.init.domain.IsAuthorizedUseCase
import com.ch000se.messenger.feature.init.domain.repositories.AuthRepository
import javax.inject.Inject

internal class IsAuthorizedUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : IsAuthorizedUseCase {

    override suspend fun invoke(): Boolean {
        return authRepository.isAuthorized()
    }
}