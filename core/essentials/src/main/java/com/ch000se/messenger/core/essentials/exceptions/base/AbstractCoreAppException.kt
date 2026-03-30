package com.ch000se.messenger.core.essentials.exceptions.base

import com.ch000se.messenger.core.essentials.resources.CoreStringProvider
import com.ch000se.messenger.core.essentials.resources.StringProviderStore

/**
 * Base exception class for all other in-app exception from the core-essentials module.
 * All these exceptions use [CoreStringProvider] for localizing error messages.
 */
abstract class AbstractCoreAppException(
    message: String,
    cause: Throwable? = null,
) : AbstractAppException(message, cause), WithLocalizedMessage {

    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String {
        return getLocalizedErrorMessage(stringProviderStore<CoreStringProvider>())
    }

    /**
     * Subclasses of this [AbstractCoreAppException] override this method to
     * specify a localized error message.
     */
    abstract fun getLocalizedErrorMessage(stringProvider: CoreStringProvider): String

}
