package com.ch000se.messenger.core.essentials.exceptions.mapper

import com.ch000se.messenger.core.essentials.exceptions.StringProviderStore
import com.ch000se.messenger.core.essentials.exceptions.WithLocalizedMessage
import com.ch000se.messenger.core.essentials.resources.CoreStringProvider
import javax.inject.Inject

class DefaultExceptionToMessageMapper @Inject constructor(
    private val stringProviderStore: StringProviderStore
) : ExceptionToMessageMapper {
    override fun getLocalizedMessage(exception: Exception): String {
        return if(exception is WithLocalizedMessage) {
            exception.getLocalizedErrorMessage(stringProviderStore)
        } else {
            stringProviderStore<CoreStringProvider>().unknownErrorMessage()
        }
    }
}