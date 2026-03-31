package com.ch000se.messenger.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import com.ch000se.messenger.core.navigation.dsl.ContextMenuItem
import com.ch000se.messenger.core.navigation.dsl.ScreenToolbar
import com.ch000se.messenger.core.navigation.dsl.ToolBarAction
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    toolBar: ScreenToolbar.Default,
    showBackButton: Boolean,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = toolBar.title
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        actions = {
            toolBar.actions.forEach { action ->
                when (action) {
                    is ToolBarAction.Default -> {
                        ToolbarActionButton(
                            icon = action.icon,
                            onClick = action.onClick
                        )
                    }

                    is ToolBarAction.ContextMenu -> {
                        ToolbarContextMenuActionButton(
                            icon = action.icon,
                            items = action.items
                        )
                    }
                }
            }
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(
                    onClick = onBackPressed
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}

@Composable
private fun ToolbarActionButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Composable
private fun ToolbarContextMenuActionButton(
    icon: ImageVector,
    items: ImmutableList<ContextMenuItem>
) {
    var isExpanded by remember { mutableStateOf(false) }

    Box {
        ToolbarActionButton(
            icon = icon,
            onClick = {
                isExpanded = true
            }
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item.title) },
                    onClick = {
                        item.onClick()
                        isExpanded = false
                    }
                )
            }
        }
    }
}