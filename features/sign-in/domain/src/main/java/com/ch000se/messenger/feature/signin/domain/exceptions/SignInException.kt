package com.ch000se.messenger.feature.signin.domain.exceptions

import com.ch000se.messenger.feature.signin.domain.resources.SignInStringProvider
import com.ch000se.messenger.feature.signin.domain.exceptions.base.AbstractSignInAppException

class SignInException : AbstractSignInAppException("Specific feature error") {

    override fun getLocalizedErrorMessage(stringProvider: SignInStringProvider): String {
        return TODO()
    }

}
