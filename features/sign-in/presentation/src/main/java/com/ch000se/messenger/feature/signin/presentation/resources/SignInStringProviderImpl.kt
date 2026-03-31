package com.ch000se.messenger.feature.signin.presentation.resources

import android.content.Context
import com.ch000se.messenger.feature.signin.domain.resources.SignInStringProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SignInStringProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : SignInStringProvider {

}
