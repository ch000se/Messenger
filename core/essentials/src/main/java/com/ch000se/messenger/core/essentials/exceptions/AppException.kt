package com.ch000se.messenger.core.essentials.exceptions

import com.ch000se.messenger.core.essentials.resources.CoreStringProvider

abstract class AbstractAppException(
    message: String,
    cause: Throwable? = null,
) : Exception(message, cause)

abstract class AbstractCoreAppException(
    message: String,
    cause: Throwable? = null,
) : AbstractAppException(message, cause), WithLocalizedMessage {

    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String {
        return getLocalizedErrorMessage(stringProviderStore<CoreStringProvider>())
    }

    abstract fun getLocalizedErrorMessage(stringProvider: CoreStringProvider): String

}

//class ConnectionException(
//    cause: Throwable? = null,
//) : AbstractCoreAppException("Network connection error", cause) {
//
//}
//
//class BackendException(
//    val httpCode: Int = 400,
//    val serverCode: String = "",
//    val backendMessage: String = "",
//    cause: Throwable? = null,
//) : AbstractCoreAppException("Server error (serverCode=$serverCode, httpCode=$httpCode): $backendMessage", cause)

class UnknownException(
    cause: Throwable
): AbstractAppException("Unknown exception", cause)