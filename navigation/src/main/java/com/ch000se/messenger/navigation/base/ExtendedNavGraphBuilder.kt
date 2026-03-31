package com.ch000se.messenger.navigation.base

import com.ch000se.messenger.core.navigation.dsl.ScreenScope
import com.ch000se.messenger.navigation.Route
import kotlin.reflect.KClass

interface ExtendedNavGraphBuilder {
    fun <T : Route> composable(
        routeClass: KClass<T>,
        content: ScreenScope.(T) -> Unit
    )
}

inline fun <reified T : Route> ExtendedNavGraphBuilder.composable(
    noinline content: ScreenScope.(T) -> Unit
) = composable(T::class, content)