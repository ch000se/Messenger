package com.ch000se.messenger.core.navigation.dsl

import android.content.Context
import androidx.compose.runtime.Composable


interface ScreenScope : ConfiguredScreen {
    val context: Context

    override var toolBar: ScreenToolbar
    fun content(block: @Composable () -> Unit)
}