package %PACKAGE%.domain

interface Save%MODULE_NAME%UseCase {
    suspend operator fun invoke(title: String)
}
