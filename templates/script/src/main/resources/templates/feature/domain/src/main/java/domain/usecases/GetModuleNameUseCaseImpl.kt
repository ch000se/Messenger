package %PACKAGE%.domain.usecases

import com.elveum.container.Container
import %PACKAGE%.domain.Get%MODULE_NAME%UseCase
import %PACKAGE%.domain.repositories.%MODULE_NAME%Repository
import com.ch000se.messenger.utils.autobinding.annotations.AutoBinds
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@AutoBinds
internal class Get%MODULE_NAME%UseCaseImpl @Inject constructor(
    private val repository: %MODULE_NAME%Repository,
) : Get%MODULE_NAME%UseCase {

    override fun invoke(): Flow<Container<String>> {
        return repository.getFeatureName()
    }

}
