package com.ch000se.messenger.feature.init.presentation.di

import com.ch000se.messenger.feature.init.domain.IsAuthorizedUseCase
import com.ch000se.messenger.feature.init.domain.ShowKeyFeatureUseCase
import com.ch000se.messenger.feature.init.presentation.usecases.IsAuthorizedUseCaseImpl
import com.ch000se.messenger.feature.init.presentation.usecases.ShowKeyFeatureUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface InitUseCaseModule {

    @Binds
    fun bindShowKeyFeatureUseCase(impl: ShowKeyFeatureUseCaseImpl): ShowKeyFeatureUseCase

    @Binds
    fun bindIsAuthorizedUseCase(impl: IsAuthorizedUseCaseImpl): IsAuthorizedUseCase
}