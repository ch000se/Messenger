import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.the

val Project.customLibs get() = the<VersionCatalogsExtension>().named("libs")

fun Project.library(name: String) = customLibs.findLibrary(name).get()