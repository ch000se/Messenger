package com.ch000se.messenger.core.navigation.dsl

import androidx.compose.runtime.Immutable

@Immutable
data class ContextMenuItem (
    val title: String,
    val onClick: () -> Unit,
)