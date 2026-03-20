package com.ch000se.templates.script

val InputArgs.camelCaseModuleName
    get() = outputModuleName
        .substringAfterLast(":")
        .split("-")
        .joinToString("") { it.capitalizeFirstLetter() }

val InputArgs.camelCaseModuleNameDecapitalized
    get() = camelCaseModuleName
        .replaceFirstChar { it.lowercaseChar() }

val InputArgs.camelCaseModuleNameSnake
    get() = outputModuleName
        .substringAfterLast(":")
        .replace('-', '_')

val InputArgs.moduleReference
    get() = outputModuleName
        .drop(1)
        .replace(":", ".")
        .split("-")
        .joinToString("") { it.capitalizeFirstLetter() }
        .replaceFirstChar { it.lowercaseChar() }

val InputArgs.moduleDependency
    get() = outputModuleName
        .drop(1)
        .replace(":", ".")
        .split("-")
        .joinToString("") { it.capitalizeFirstLetter() }
        .replaceFirstChar { it.lowercaseChar() }

private fun String.capitalizeFirstLetter() = replaceFirstChar { it.uppercaseChar() }

fun InputArgs.replacePlaceholders(content: String): String {
    return content
        .replace("%PACKAGE%", outputModulePackage)
        .replace("%MODULE_NAME%", camelCaseModuleName)
        .replace("%MODULE_NAME_DECAPITALIZED%", camelCaseModuleNameDecapitalized)
        .replace("%MODULE_NAME_SNAKE%", camelCaseModuleNameSnake)
        .replace("%MODULE_REFERENCE%", moduleReference)
        .replace("%MODULE_DEPENDENCY%", moduleDependency)
}
