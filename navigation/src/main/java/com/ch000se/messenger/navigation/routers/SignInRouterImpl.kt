package com.ch000se.messenger.navigation.routers

import com.ch000se.messenger.feature.signin.presentation.SignInRouter
import com.ch000se.messenger.navigation.base.AppRouter
import javax.inject.Inject

class SignInRouterImpl @Inject constructor(
    private val appRouter: AppRouter,
) : SignInRouter {

    override fun navigateUp() {
        appRouter.goBack()
    }

}
