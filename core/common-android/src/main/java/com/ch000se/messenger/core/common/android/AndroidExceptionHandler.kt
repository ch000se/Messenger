package com.ch000se.messenger.core.common.android

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ch000se.common.android.R
import com.ch000se.messenger.core.essentials.exceptions.handler.ExceptionHandler
import com.ch000se.messenger.core.essentials.exceptions.mapper.ExceptionToMessageMapper
import dagger.hilt.android.scopes.ActivityRetainedScoped
import jakarta.inject.Inject

@ActivityRetainedScoped // This scope is used to ensure that the exception handler survives configuration changes, such as screen rotations, while still being tied to the lifecycle of the activity.
class AndroidExceptionHandler @Inject constructor(
    private val exceptionToMessageMapper: ExceptionToMessageMapper
) : ExceptionHandler {

    private val errorMessageState = mutableStateOf<String?>(null)

    override fun handleException(exception: Exception) {
        val message = exceptionToMessageMapper.getLocalizedMessage(exception)
        errorMessageState.value = message
    }

    @Composable
    fun ErrorDialog(modifier: Modifier = Modifier) {
        errorMessageState.value?.let { message ->
            AlertDialog(
                onDismissRequest = { errorMessageState.value = null },
                confirmButton = {
                    TextButton(onClick = { errorMessageState.value = null }) {
                        Text(stringResource(R.string.ok))
                    }
                },
                title = { Text(stringResource(R.string.error)) },
                text = { Text(message) }
            )
        }

    }
}