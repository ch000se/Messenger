package com.ch000se.messenger.feature.signin.domain

import com.elveum.container.Container
import kotlinx.coroutines.flow.Flow

interface GetSignInUseCase {
    operator fun invoke(): Flow<Container<String>>
}
