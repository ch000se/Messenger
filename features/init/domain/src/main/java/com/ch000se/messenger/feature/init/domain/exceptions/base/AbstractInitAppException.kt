package com.ch000se.messenger.feature.init.domain.exceptions.base

import com.ch000se.messenger.core.essentials.exceptions.base.AbstractAppException
import com.ch000se.messenger.core.essentials.exceptions.base.WithLocalizedMessage
import com.ch000se.messenger.core.essentials.resources.StringProviderStore
import com.ch000se.messenger.feature.init.domain.resources.InitStringProvider

abstract class AbstractInitAppException(
    message: String,
    cause: Throwable? = null,
) : AbstractAppException(message, cause), WithLocalizedMessage {

    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String {
        return getLocalizedErrorMessage(stringProviderStore<InitStringProvider>())
    }

    abstract fun getLocalizedErrorMessage(stringProvider: InitStringProvider): String
}
