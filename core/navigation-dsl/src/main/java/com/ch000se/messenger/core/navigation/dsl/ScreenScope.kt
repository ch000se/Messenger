package com.ch000se.messenger.core.navigation.dsl

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlin.reflect.KClass


interface ScreenScope : ConfiguredScreen {
    val context: Context
    val coroutineScope: CoroutineScope
    fun <T : ViewModel> viewModel(vmClass: KClass<T>): T

    override var toolBar: ScreenToolbar
    fun content(block: @Composable () -> Unit)
}

inline fun <reified T : ViewModel> ScreenScope.viewModel(): T {
    return viewModel(T::class)
}