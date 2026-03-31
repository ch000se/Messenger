package com.ch000se.messenger.feature.signin.demo

import com.ch000se.messenger.feature.signin.domain.repositories.SignInRepository
import com.elveum.container.Container
import com.elveum.container.subject.LazyFlowSubject
import com.elveum.container.subject.listenReloadable
import com.elveum.container.subject.updateIfSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DemoSignInRepository @Inject constructor() : SignInRepository {

    private val subject = LazyFlowSubject.create {
        delay(1000)
        emit("SignIn Feature")
    }

    override fun getFeatureName(): Flow<Container<String>> {
        return subject.listenReloadable()
    }

    override suspend fun setFeatureName(newName: String) {
        delay(1000)
        subject.updateIfSuccess { newName }
    }

}
