package %PACKAGE%.domain

import com.elveum.container.Container
import kotlinx.coroutines.flow.Flow

interface Get%MODULE_NAME%UseCase {
    operator fun invoke(): Flow<Container<String>>
}
