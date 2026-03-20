package com.ch000se.templates.script.dependencies

import com.ch000se.templates.script.Templates
import com.ch000se.templates.script.dependencies.base.AbstractDependency

class KspDependency : AbstractDependency {

    override val supportedTemplates = setOf(
        Templates.AndroidLibrary,
        Templates.KotlinLibrary,
    )

    override val pluginsBlock: String = """
        |    alias(libs.plugins.ksp)
    """.trimMargin()

    override val librariesBlock: String = ""

}
