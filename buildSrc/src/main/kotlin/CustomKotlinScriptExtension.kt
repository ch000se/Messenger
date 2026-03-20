import org.gradle.api.provider.Property

interface CustomKotlinScriptExtension {
    val mainClass: Property<String>
    val archiveName: Property<String>
}