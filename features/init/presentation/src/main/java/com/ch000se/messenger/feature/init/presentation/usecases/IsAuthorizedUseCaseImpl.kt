package com.ch000se.messenger.feature.init.presentation.usecases

import com.ch000se.messenger.feature.init.domain.IsAuthorizedUseCase
import javax.inject.Inject

class IsAuthorizedUseCaseImpl @Inject constructor() : IsAuthorizedUseCase {

    override suspend fun invoke(): Boolean {
        // TODO: Replace with actual authorization check
        return false
    }
}