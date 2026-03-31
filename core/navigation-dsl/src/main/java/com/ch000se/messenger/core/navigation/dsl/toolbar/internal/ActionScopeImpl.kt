package com.ch000se.messenger.core.navigation.dsl.toolbar.internal

import androidx.compose.ui.graphics.vector.ImageVector
import com.ch000se.messenger.core.navigation.dsl.toolbar.ActionScope

internal class ActionScopeImpl : ActionScope {
    override var icon: ImageVector = EmptyImageVector
    override var onClick: () -> Unit = {}
}
