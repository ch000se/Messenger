package com.ch000se.messenger.core.common.android.di

import com.ch000se.messenger.core.common.android.AndroidExceptionHandler
import com.ch000se.messenger.core.essentials.exceptions.handler.ExceptionHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface ExceptionHandlerModule {

    @Binds
    fun bindExceptionHandler(impl: AndroidExceptionHandler): ExceptionHandler
}