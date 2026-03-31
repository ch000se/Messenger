package com.ch000se.messenger.navigation.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import com.ch000se.messenger.core.navigation.dsl.ConfiguredScreen
import com.ch000se.messenger.core.navigation.dsl.ScreenScope
import com.ch000se.messenger.navigation.Route
import kotlin.reflect.KClass

interface ExtendedNavStore {

    val screen: ConfiguredScreen
    fun <T : Route> registerConfiguration(
        routeClass: KClass<T>,
        content: ScreenScope.(T) -> Unit
    )

    @Composable
    fun <T : Route> Content(route: T, id: String)

    fun onBackStackChanged(backStack: List<NavBackStackEntry>)
}