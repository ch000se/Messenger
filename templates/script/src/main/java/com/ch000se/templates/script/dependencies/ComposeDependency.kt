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
        |    implementation(libs.androidx.ui)
        |    implementation(libs.androidx.ui.graphics)
        |    implementation(libs.androidx.ui.tooling.preview)
        |    implementation(libs.androidx.material3)
        |    debugImplementation(libs.androidx.ui.tooling)
        |    debugImplementation(libs.androidx.ui.test.manifest)
    """.trimMargin()

    override val configurationBlock: String = "setupCompose()"

}
