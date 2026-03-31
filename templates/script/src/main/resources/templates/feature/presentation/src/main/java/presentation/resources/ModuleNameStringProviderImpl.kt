package %PACKAGE%.presentation.resources

import android.content.Context
import %PACKAGE%.domain.resources.%MODULE_NAME%StringProvider
import %PACKAGE%.presentation.R
import com.ch000se.messenger.utils.autobinding.annotations.AutoBinds
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@AutoBinds
class %MODULE_NAME%StringProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
): %MODULE_NAME%StringProvider {

}
