package com.ch000se.messenger.navigation.di

import com.ch000se.messenger.feature.init.presentation.InitRouter
import com.ch000se.messenger.feature.signin.presentation.SignInRouter
import com.ch000se.messenger.navigation.routers.InitRouterImpl
import com.ch000se.messenger.navigation.routers.SignInRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
interface RoutersModule {

    @Binds
    fun bindInitRouter(
        impl: InitRouterImpl
    ): InitRouter

    @Binds
    fun bindSignInRouter(
        impl: SignInRouterImpl
    ): SignInRouter
}