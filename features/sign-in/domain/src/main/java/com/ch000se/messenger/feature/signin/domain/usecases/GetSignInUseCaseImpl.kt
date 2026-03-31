package com.ch000se.messenger.feature.signin.domain.usecases

import com.ch000se.messenger.core.essentials.Container
import com.ch000se.messenger.feature.signin.domain.GetSignInUseCase
import com.ch000se.messenger.feature.signin.domain.repositories.SignInRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSignInUseCaseImpl @Inject constructor(
    private val repository: SignInRepository,
) : GetSignInUseCase {

    override fun invoke(): Flow<Container<String>> {
        return repository.getFeatureName()
    }

}
