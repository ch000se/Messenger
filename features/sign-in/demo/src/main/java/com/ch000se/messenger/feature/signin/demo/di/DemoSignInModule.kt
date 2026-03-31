package com.ch000se.messenger.feature.signin.demo.di

import com.ch000se.messenger.feature.signin.demo.DemoSignInRepository
import com.ch000se.messenger.feature.signin.domain.repositories.SignInRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DemoSignInModule {

    @Binds
    fun bindSignInRepository(impl: DemoSignInRepository): SignInRepository

}
