package com.ch000se.messenger.navigation.routers

import com.ch000se.messenger.feature.init.presentation.InitRouter
import com.ch000se.messenger.navigation.SignInRoute
import com.ch000se.messenger.navigation.base.AppRouter
import javax.inject.Inject

class InitRouterImpl @Inject constructor(
    private val appRouter: AppRouter,
) : InitRouter {
    override fun launchSignIn() {
        appRouter.restart(SignInRoute)
    }

}