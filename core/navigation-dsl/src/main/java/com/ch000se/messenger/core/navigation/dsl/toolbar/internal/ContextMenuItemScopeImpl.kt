package com.ch000se.messenger.core.navigation.dsl.toolbar.internal

import android.content.Context
import com.ch000se.messenger.core.navigation.dsl.toolbar.ContextMenuItemScope

internal class ContextMenuItemScopeImpl(
    private val context: Context,
) : ContextMenuItemScope {
    override var title: String = "Item"
    override var titleRes: Int = 0
        set(value) {
            field = value
            title = context.getString(value)
        }
    override var onClick: () -> Unit = {}
}
