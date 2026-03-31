package com.ch000se.messenger.feature.signin.domain.usecases

import com.elveum.container.Container
import com.ch000se.messenger.feature.signin.domain.GetSignInUseCase
import com.ch000se.messenger.feature.signin.domain.repositories.SignInRepository
import com.ch000se.messenger.utils.autobinding.annotations.AutoBinds
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@AutoBinds
internal class GetSignInUseCaseImpl @Inject constructor(
    private val repository: SignInRepository,
) : GetSignInUseCase {

    override fun invoke(): Flow<Container<String>> {
        return repository.getFeatureName()
    }

}
