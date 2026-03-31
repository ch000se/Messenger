package com.ch000se.messenger.core.navigation.dsl.toolbar

import androidx.compose.ui.graphics.vector.ImageVector
import com.ch000se.messenger.core.navigation.dsl.ScreenScope
import com.ch000se.messenger.core.navigation.dsl.ScreenToolbar
import com.ch000se.messenger.core.navigation.dsl.toolbar.internal.ToolbarScopeImpl
import kotlinx.collections.immutable.toImmutableList


fun ScreenScope.toolbar(
    block: ToolbarScope.() -> Unit
) {
    val toolbarScope = ToolbarScopeImpl(context)
    toolbarScope.apply(block)
    toolBar = ScreenToolbar.Default(
        title = toolbarScope.title,
        actions = toolbarScope.actions.toImmutableList()
    )
}


interface ToolbarScope {

    var title: String

    var titleRes: Int

    fun action(block: ActionScope.() -> Unit)

    fun contextMenuAction(block: ContextMenuActionScope.() -> Unit)

}

interface ActionScope {


    var icon: ImageVector


    var onClick: () -> Unit

}


interface ContextMenuActionScope {


    var icon: ImageVector


    fun item(block: ContextMenuItemScope.() -> Unit)
}


interface ContextMenuItemScope {


    var title: String

    var titleRes: Int

    var onClick: () -> Unit

}
