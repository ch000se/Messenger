package com.ch000se.messenger.navigation.base

import com.ch000se.messenger.navigation.Route

interface AppRouter {

    fun launch(route: Route)
    fun restart(route: Route)
    fun goBack()
}