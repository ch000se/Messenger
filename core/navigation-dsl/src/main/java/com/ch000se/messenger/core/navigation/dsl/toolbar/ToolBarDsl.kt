package com.ch000se.messenger.core.navigation.dsl.toolbar

import androidx.compose.ui.graphics.vector.ImageVector
import com.ch000se.messenger.core.navigation.dsl.ContextMenuItem
import com.ch000se.messenger.core.navigation.dsl.ScreenScope
import com.ch000se.messenger.core.navigation.dsl.ScreenToolbar
import com.ch000se.messenger.core.navigation.dsl.ToolBarAction
import kotlinx.collections.immutable.toImmutableList

fun ScreenScope.toolBar(
    block: ToolBarScope.() -> Unit
) {
    val toolBarScopeImpl = ToolBarScopeImpl()
    toolBarScopeImpl.apply(block)
    toolBar = ScreenToolbar.Default(
        title = toolBarScopeImpl.title,
        actions = toolBarScopeImpl.actions.toImmutableList()
    )
}

interface ToolBarScope {
    var title: String
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
    var onClick: () -> Unit
}

internal class ToolBarScopeImpl : ToolBarScope {
    override var title: String = ""
    internal val actions = mutableListOf<ToolBarAction>()

    override fun action(block: ActionScope.() -> Unit) {
        val actionScopeImpl = ActionScopeImpl()
        actionScopeImpl.apply(block)
        actions.add(
            ToolBarAction.Default(
                icon = actionScopeImpl.icon,
                onClick = actionScopeImpl.onClick
            )
        )
    }

    override fun contextMenuAction(block: ContextMenuActionScope.() -> Unit) {
        val contextMenuActionScopeImpl = ContextMenuActionScopeImpl()
        contextMenuActionScopeImpl.apply(block)
        actions.add(
            ToolBarAction.ContextMenu(
                icon = contextMenuActionScopeImpl.icon,
                items = contextMenuActionScopeImpl.menuItems.toImmutableList()
            )
        )
    }
}

internal class ContextMenuActionScopeImpl : ContextMenuActionScope {
    override var icon: ImageVector = Icons.Default.MoreVert
    internal val menuItems = mutableListOf<ContextMenuItem>()

    override fun item(block: ContextMenuItemScope.() -> Unit) {
        val contextMenuItemScopeImpl = ContextMenuItemScopeImpl()
        contextMenuItemScopeImpl.apply(block)
        menuItems.add(
            ContextMenuItem(
                title = contextMenuItemScopeImpl.title,
                onClick = contextMenuItemScopeImpl.onClick
            )
        )
    }

}

internal class ContextMenuItemScopeImpl : ContextMenuItemScope {
    private var _title: String? = null

    override var title: String
        get() = requireNotNull(_title)
        set(value) {
            _title = value
        }

    override var onClick: () -> Unit = {}
}

internal class ActionScopeImpl : ActionScope {
    private var _icon: ImageVector? = null

    override var icon: ImageVector
        get() = requireNotNull(_icon)
        set(value) {
            _icon = value
        }
    override var onClick: () -> Unit = {}
}