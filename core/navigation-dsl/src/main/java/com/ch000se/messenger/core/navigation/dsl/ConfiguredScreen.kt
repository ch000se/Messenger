package com.ch000se.messenger.core.navigation.dsl

interface ConfiguredScreen {
    val toolBar: ScreenToolbar

    data object Empty : ConfiguredScreen {
        override val toolBar = ScreenToolbar.Hidden
    }
}