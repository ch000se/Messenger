import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

fun Project.setupCompose() {
    extensions.findByType(LibraryExtension::class.java)?.let {
        it.buildFeatures.compose = true
    }
    extensions.findByType(ApplicationExtension::class.java)?.let {
        it.buildFeatures.compose = true
    }
    extensions.configure<ComposeCompilerGradlePluginExtension> {
        reportsDestination = layout.buildDirectory.dir("compose_compiler")
        metricsDestination = layout.buildDirectory.dir("compose_compiler")
        stabilityConfigurationFiles.add(rootProject.layout.projectDirectory.file("compose_compiler_config.conf"))
    }
}
