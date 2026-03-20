package com.ch000se.messenger.core.common.android.di

import com.ch000se.messenger.core.common.android.logger.AndroidLogger
import com.ch000se.messenger.core.essentials.logger.Logger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CommonAndroidModule {

    @Binds
    fun bindLogger(logger: AndroidLogger): Logger
}