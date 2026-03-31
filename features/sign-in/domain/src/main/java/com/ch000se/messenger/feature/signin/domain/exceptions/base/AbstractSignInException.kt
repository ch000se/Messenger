package com.ch000se.messenger.feature.signin.domain.exceptions.base

import com.ch000se.messenger.core.essentials.exceptions.base.AbstractAppException
import com.ch000se.messenger.core.essentials.exceptions.base.WithLocalizedMessage
import com.ch000se.messenger.core.essentials.resources.StringProviderStore
import com.ch000se.messenger.feature.signin.domain.resources.SignInStringProvider

abstract class AbstractSignInAppException(
    message: String,
    cause: Throwable? = null,
) : AbstractAppException(message, cause), WithLocalizedMessage {

    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String {
        return getLocalizedErrorMessage(stringProviderStore<SignInStringProvider>())
    }

    abstract fun getLocalizedErrorMessage(stringProvider: SignInStringProvider): String
}
