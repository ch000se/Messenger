package com.ch000se.templates.script.dependencies.base

import kotlin.reflect.KClass

interface AbstractDependency {

    val supportedTemplates: Set<String>

    val include: List<KClass<*>> get() = emptyList()

    val pluginsBlock: String

    val librariesBlock: String

    val configurationBlock: String get() = ""

}
