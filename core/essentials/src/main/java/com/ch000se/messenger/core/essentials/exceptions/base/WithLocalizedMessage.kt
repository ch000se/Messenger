package com.ch000se.messenger.core.essentials.exceptions.base

import com.ch000se.messenger.core.essentials.resources.StringProviderStore

/**
 * An optional marker interface that can be implemented by any exception class
 * to add a support of localized error messages.
 */
interface WithLocalizedMessage {

    /**
     * Get a localized error message for an exception that implements this interface.
     */
    fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String

}
