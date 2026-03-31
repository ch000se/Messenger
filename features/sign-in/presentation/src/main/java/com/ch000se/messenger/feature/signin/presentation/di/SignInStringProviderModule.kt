package com.ch000se.messenger.feature.signin.presentation.di

import com.ch000se.messenger.core.essentials.resources.StringProvider
import com.ch000se.messenger.feature.signin.domain.resources.SignInStringProvider
import com.ch000se.messenger.feature.signin.presentation.resources.SignInStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface SignInStringProviderModule {

    @Binds
    @IntoMap
    @ClassKey(SignInStringProvider::class)
    fun bindSignInStringProviderIntoMap(
        impl: SignInStringProviderImpl
    ): StringProvider

}
