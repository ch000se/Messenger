package com.ch000se.messenger.core.navigation.dsl

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.collections.immutable.ImmutableList

@Immutable
sealed class ToolBarAction {
    abstract val icon: ImageVector

    @Immutable
    data class Default(
        override val icon: ImageVector,
        val onClick: () -> Unit
    ) : ToolBarAction()

    @Immutable
    data class ContextMenu(
        override val icon: ImageVector,
        val items: ImmutableList<ContextMenuItem>
    ) : ToolBarAction()
}