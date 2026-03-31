package com.ch000se.messenger.feature.signin.domain


import com.ch000se.messenger.core.essentials.Container
import kotlinx.coroutines.flow.Flow

interface GetSignInUseCase {
    operator fun invoke(): Flow<Container<String>>
}
