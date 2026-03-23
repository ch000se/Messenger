package com.ch000se.messenger.core.essentials.exceptions.mapper

class EmptyExceptionToMessageMapper: ExceptionToMessageMapper {
    override fun getLocalizedMessage(exception: Exception): String {
        return exception.localizedMessage ?: "An error occurred"
    }
}