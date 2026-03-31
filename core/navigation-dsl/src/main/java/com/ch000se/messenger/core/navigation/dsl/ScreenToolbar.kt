package com.ch000se.messenger.core.navigation.dsl

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
sealed class ScreenToolbar {
    @Immutable
    data object Hidden : ScreenToolbar()

    @Immutable
    data class Default(
        val title: String,
        val actions: ImmutableList<ToolBarAction> = persistentListOf()
    ) : ScreenToolbar()
}