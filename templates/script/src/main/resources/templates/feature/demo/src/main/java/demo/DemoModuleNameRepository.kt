package %PACKAGE%.demo

import %PACKAGE%.domain.repositories.%MODULE_NAME%Repository
import com.ch000se.messenger.utils.autobinding.annotations.AutoBinds
import com.elveum.container.Container
import com.elveum.container.subject.LazyFlowSubject
import com.elveum.container.subject.listenReloadable
import com.elveum.container.subject.updateIfSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@AutoBinds
@Singleton
class Demo%MODULE_NAME%Repository @Inject constructor() : %MODULE_NAME%Repository {

    private val subject = LazyFlowSubject.create {
        delay(1000)
        emit("%MODULE_NAME% Feature")
    }

    override fun getFeatureName(): Flow<Container<String>> {
        return subject.listenReloadable()
    }

    override suspend fun setFeatureName(newName: String) {
        delay(1000)
        subject.updateIfSuccess { newName }
    }

}
