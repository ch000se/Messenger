package com.ch000se.messenger.core.essentials.exceptions

interface WithLocalizedMessage {
    fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String
}