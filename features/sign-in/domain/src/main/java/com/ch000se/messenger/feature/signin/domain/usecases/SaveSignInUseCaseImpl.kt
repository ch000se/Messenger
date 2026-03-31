package com.ch000se.messenger.feature.signin.domain.usecases

import com.ch000se.messenger.feature.signin.domain.SaveSignInUseCase
import com.ch000se.messenger.feature.signin.domain.repositories.SignInRepository
import com.ch000se.messenger.utils.autobinding.annotations.AutoBinds
import javax.inject.Inject

@AutoBinds
internal class SaveSignInUseCaseImpl @Inject constructor(
    private val repository: SignInRepository,
) : SaveSignInUseCase {

    override suspend fun invoke(title: String) {
        repository.setFeatureName(title)
    }

}
