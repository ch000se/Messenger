package com.ch000se.messenger.core.navigation.dsl.toolbar.internal

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.ui.graphics.vector.ImageVector
import com.ch000se.messenger.core.navigation.dsl.ContextMenuItem
import com.ch000se.messenger.core.navigation.dsl.toolbar.ContextMenuActionScope
import com.ch000se.messenger.core.navigation.dsl.toolbar.ContextMenuItemScope

internal class ContextMenuActionScopeImpl(
    private val context: Context
) : ContextMenuActionScope {

    override var icon: ImageVector = Icons.Default.MoreVert
    internal val menuItems = mutableListOf<ContextMenuItem>()

    override fun item(block: ContextMenuItemScope.() -> Unit) {
        val scope = ContextMenuItemScopeImpl(context)
        scope.apply(block)
        menuItems.add(
            ContextMenuItem(
                title = scope.title,
                onClick = scope.onClick,
            )
        )
    }

}
