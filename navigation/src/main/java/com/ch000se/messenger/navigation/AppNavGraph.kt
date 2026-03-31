package com.ch000se.messenger.navigation

import com.ch000se.messenger.feature.init.presentation.initScreen
import com.ch000se.messenger.feature.signin.presentation.signInScreen
import com.ch000se.messenger.navigation.base.ExtendedNavGraphBuilder
import com.ch000se.messenger.navigation.base.composable

fun ExtendedNavGraphBuilder.buildAppNavGraph() {
    composable<InitRoute> {
        initScreen()
    }
    composable<SignInRoute> {
        signInScreen()
    }
}