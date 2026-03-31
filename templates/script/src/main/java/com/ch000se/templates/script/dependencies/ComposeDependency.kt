package com.ch000se.templates.script.dependencies

import com.ch000se.templates.script.Templates
import com.ch000se.templates.script.dependencies.base.AbstractDependency

class ComposeDependency : AbstractDependency {

    override val supportedTemplates = setOf(
        Templates.AndroidLibrary,
    )

    override val pluginsBlock: String = """
        |    alias(libs.plugins.compose)
    """.trimMargin()

    override val librariesBlock: String = """
        |    implementation(platform(libs.androidx.compose.bom))
        |    implementation(libs.androidx.compose.ui)
        |    implementation(libs.androidx.compose.ui.graphics)
        |    implementation(libs.androidx.compose.ui.tooling.preview)
        |    implementation(libs.androidx.compose.material3)
        |    debugImplementation(libs.androidx.compose.ui.tooling)
        |    debugImplementation(libs.androidx.compose.ui.test.manifest)
    """.trimMargin()

    override val configurationBlock: String = "setupCompose()"
}