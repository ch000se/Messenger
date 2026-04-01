package com.ch000se.messenger.feature.init.domain.di

import com.ch000se.messenger.feature.init.domain.IsAuthorizedUseCase
import com.ch000se.messenger.feature.init.domain.ShowKeyFeatureUseCase
import com.ch000se.messenger.feature.init.domain.usecases.IsAuthorizedUseCaseImpl
import com.ch000se.messenger.feature.init.domain.usecases.ShowKeyFeatureUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCasesModule {

    @Binds
    fun bindIsAuthorizedUseCase(
        impl: IsAuthorizedUseCaseImpl
    ): IsAuthorizedUseCase

    @Binds
    fun bindShowKeyFeatureUseCase(
        impl: ShowKeyFeatureUseCaseImpl
    ): ShowKeyFeatureUseCase
}