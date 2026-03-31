package com.ch000se.templates.script.dependencies

import com.ch000se.templates.script.Templates
import com.ch000se.templates.script.dependencies.base.AbstractDependency
import kotlin.reflect.KClass

class HiltDependency : AbstractDependency {

    override val supportedTemplates = setOf(
        Templates.AndroidLibrary,
    )

    override val include: List<KClass<*>> = listOf(
        KspDependency::class
    )

    override val pluginsBlock: String = """
        |    alias(libs.plugins.hilt)
    """.trimMargin()

    override val librariesBlock: String = """
        |    implementation(libs.hilt.android)
        |    ksp(libs.hilt.compiler)
    """.trimMargin()
}