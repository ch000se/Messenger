package com.ch000se.messenger.feature.signin.domain.usecases

import com.ch000se.messenger.feature.signin.domain.SaveSignInUseCase
import com.ch000se.messenger.feature.signin.domain.repositories.SignInRepository
import javax.inject.Inject

class SaveSignInUseCaseImpl @Inject constructor(
    private val repository: SignInRepository,
) : SaveSignInUseCase {

    override suspend fun invoke(title: String) {
        repository.setFeatureName(title)
    }

}
