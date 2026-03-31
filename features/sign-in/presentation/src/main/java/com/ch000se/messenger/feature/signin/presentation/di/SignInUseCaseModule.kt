package com.ch000se.messenger.feature.signin.presentation.di

import com.ch000se.messenger.feature.signin.domain.GetSignInUseCase
import com.ch000se.messenger.feature.signin.domain.SaveSignInUseCase
import com.ch000se.messenger.feature.signin.domain.usecases.GetSignInUseCaseImpl
import com.ch000se.messenger.feature.signin.domain.usecases.SaveSignInUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SignInUseCaseModule {

    @Binds
    fun bindGetSignInUseCase(impl: GetSignInUseCaseImpl): GetSignInUseCase

    @Binds
    fun bindSaveSignInUseCase(impl: SaveSignInUseCaseImpl): SaveSignInUseCase

}
