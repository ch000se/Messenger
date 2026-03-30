package com.ch000se.messenger.core.essentials.exceptions.base
/**
 * Base top-level exception class for all other in-app exceptions.
 */
abstract class AbstractAppException(
    message: String,
    cause: Throwable? = null,
) : Exception(message, cause)
