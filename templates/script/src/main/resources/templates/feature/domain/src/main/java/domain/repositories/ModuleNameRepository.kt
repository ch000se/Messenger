package %PACKAGE%.domain.repositories

import com.elveum.container.Container
import kotlinx.coroutines.flow.Flow

interface %MODULE_NAME%Repository {
    fun getFeatureName(): Flow<Container<String>>
    suspend fun setFeatureName(newName: String)
}
