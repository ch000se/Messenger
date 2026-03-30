package com.ch000se.messenger.core.essentials.resources

/**
 * Error messages provider for all exceptions from the core-essentials module.
 */
interface CoreStringProvider : StringProvider {
    val connectionErrorMessage: String
    val authErrorMessage: String
    val unknownErrorMessage: String
    val invalidBackendResponseErrorMessage: String
    val tooManyRequests: String
    val deleteAction: String
    val cancelAction: String
    fun backendErrorMessage(
        code: Int,
        backendMessage: String,
    ): String

    fun unknownErrorMessage(): String
}
