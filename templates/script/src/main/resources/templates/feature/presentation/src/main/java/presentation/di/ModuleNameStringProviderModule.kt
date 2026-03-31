package %PACKAGE%.presentation.di

import com.ch000se.messenger.core.essentials.resources.StringProvider
import %PACKAGE%.domain.resources.%MODULE_NAME%StringProvider
import %PACKAGE%.presentation.resources.%MODULE_NAME%StringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface %MODULE_NAME%StringProviderModule {

    @Binds
    @IntoMap
    @ClassKey(%MODULE_NAME%StringProvider::class)
    fun bind%MODULE_NAME%StringProviderIntoMap(
        impl: %MODULE_NAME%StringProviderImpl
    ): StringProvider

}
