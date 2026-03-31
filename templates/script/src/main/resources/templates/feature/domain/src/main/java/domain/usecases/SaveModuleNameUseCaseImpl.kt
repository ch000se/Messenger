package %PACKAGE%.domain.usecases

import %PACKAGE%.domain.Save%MODULE_NAME%UseCase
import %PACKAGE%.domain.repositories.%MODULE_NAME%Repository
import com.ch000se.messenger.utils.autobinding.annotations.AutoBinds
import javax.inject.Inject

@AutoBinds
internal class Save%MODULE_NAME%UseCaseImpl @Inject constructor(
    private val repository: %MODULE_NAME%Repository,
) : Save%MODULE_NAME%UseCase {

    override suspend fun invoke(title: String) {
        repository.setFeatureName(title)
    }

}
