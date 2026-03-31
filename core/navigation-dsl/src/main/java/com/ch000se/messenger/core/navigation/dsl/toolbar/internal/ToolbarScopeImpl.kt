package com.ch000se.messenger.core.navigation.dsl.toolbar.internal

import android.content.Context
import com.ch000se.messenger.core.navigation.dsl.ToolBarAction
import com.ch000se.messenger.core.navigation.dsl.toolbar.ActionScope
import com.ch000se.messenger.core.navigation.dsl.toolbar.ContextMenuActionScope
import com.ch000se.messenger.core.navigation.dsl.toolbar.ToolbarScope
import kotlinx.collections.immutable.toImmutableList

internal class ToolbarScopeImpl(
    private val context: Context
) : ToolbarScope {

    override var title: String = "Title"
    override var titleRes: Int = 0
        set(value) {
            field = value
            title = context.getString(value)
        }

    internal val actions = mutableListOf<ToolBarAction>()

    override fun action(block: ActionScope.() -> Unit) {
        val actionScope = ActionScopeImpl()
        actionScope.apply(block)
        actions.add(
            ToolBarAction.Default(
                icon = actionScope.icon,
                onClick = actionScope.onClick,
            )
        )
    }

    override fun contextMenuAction(block: ContextMenuActionScope.() -> Unit) {
        val contextMenuActionScope = ContextMenuActionScopeImpl(context)
        contextMenuActionScope.apply(block)
        actions.add(
            ToolBarAction.ContextMenu(
                icon = contextMenuActionScope.icon,
                items = contextMenuActionScope.menuItems.toImmutableList()
            )
        )
    }
}
