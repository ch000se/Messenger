package %PACKAGE%.domain.exceptions.base

import com.ch000se.messenger.core.essentials.exceptions.base.AbstractAppException
import com.ch000se.messenger.core.essentials.exceptions.base.WithLocalizedMessage
import com.ch000se.messenger.core.essentials.resources.StringProviderStore
import %PACKAGE%.domain.resources.%MODULE_NAME%StringProvider

abstract class Abstract%MODULE_NAME%AppException(
    message: String,
    cause: Throwable? = null,
) : AbstractAppException(message, cause), WithLocalizedMessage {

    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String {
        return getLocalizedErrorMessage(stringProviderStore<%MODULE_NAME%StringProvider>())
    }

    abstract fun getLocalizedErrorMessage(stringProvider: %MODULE_NAME%StringProvider): String
}
