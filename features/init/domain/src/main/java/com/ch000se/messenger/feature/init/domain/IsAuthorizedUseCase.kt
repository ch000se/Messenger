package com.ch000se.messenger.feature.init.domain

interface IsAuthorizedUseCase {
    suspend operator fun invoke(): Boolean
}
