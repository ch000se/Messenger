package com.ch000se.messenger.core.common.android

import android.content.Context
import com.ch000se.messenger.core.essentials.resources.CoreStringProvider
import com.ch000se.templates.android_library.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CoreStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CoreStringProvider {
    override val connectionErrorMessage = context.getString(R.string.connection_error_message)

    override fun backendErrorMessage(
        code: Int,
        backendMessage: String
    ) = context.getString(R.string.backend_error_message, code, backendMessage)

    override fun unknownErrorMessage() = context.getString(R.string.unknown_error_message)
}