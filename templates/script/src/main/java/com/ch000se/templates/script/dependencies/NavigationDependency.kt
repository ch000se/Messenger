package com.ch000se.templates.script.dependencies

import com.ch000se.templates.script.Templates
import com.ch000se.templates.script.dependencies.base.AbstractDependency
import kotlin.reflect.KClass

class NavigationDependency : AbstractDependency {

    override val supportedTemplates = setOf(
        Templates.AndroidLibrary,
    )

    override val include: List<KClass<*>> = listOf(
        HiltDependency::class,
        ComposeDependency::class,
    )

    override val pluginsBlock: String = """
        |    alias(libs.plugins.kotlin.serialization)
    """.trimMargin()

    override val librariesBlock: String = """
        |    implementation(libs.hilt.navigation.compose)
        |    implementation(libs.navigation.compose)
    """.trimMargin()
}