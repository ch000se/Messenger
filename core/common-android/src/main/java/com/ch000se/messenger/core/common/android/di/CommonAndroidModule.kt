package com.ch000se.messenger.core.common.android.di

import com.ch000se.messenger.core.common.android.CoreStringProviderImpl
import com.ch000se.messenger.core.common.android.logger.AndroidLogger
import com.ch000se.messenger.core.essentials.exceptions.mapper.DefaultExceptionToMessageMapper
import com.ch000se.messenger.core.essentials.exceptions.mapper.ExceptionToMessageMapper
import com.ch000se.messenger.core.essentials.logger.Logger
import com.ch000se.messenger.core.essentials.resources.CoreStringProvider
import com.ch000se.messenger.core.essentials.resources.StringProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface CommonAndroidModule {

    @Binds
    fun bindLogger(logger: AndroidLogger): Logger

    @Binds
    @IntoMap
    @ClassKey(CoreStringProvider::class)
    fun bindEssentialsStringProvider(impl: CoreStringProviderImpl): StringProvider

    @Binds
    fun bindExceptionToMessageMapper(impl: DefaultExceptionToMessageMapper): ExceptionToMessageMapper
}