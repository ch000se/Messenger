package com.ch000se.templates.script.dependencies.base

object DependencyRegistry {

    fun resolveDependencies(
        templateName: String,
        dependencyNames: Set<String>,
    ): List<AbstractDependency> {
        val dependencies = dependencyNames
            .map(::getDependencyClass)
            .associateWith<Class<*>, AbstractDependency?> { clazz -> null }
            .toMutableMap()
        do {
            val nonResolvedClasses = dependencies
                .filter { it.value == null }
                .keys
            val resolvedDependencies = nonResolvedClasses.associateWith(::getDependency)
            dependencies.putAll(resolvedDependencies)
            resolvedDependencies.values.forEach { dependency ->
                dependency.include.forEach {
                    if (!dependencies.contains(it.java)) {
                        dependencies[it.java] = null
                    }
                }
            }
        } while (dependencies.any { it.value == null })
        return dependencies.values
            .filterNotNull()
            .filter { it.supportedTemplates.contains(templateName) }
    }

    private fun getDependencyClass(name: String): Class<*> {
        val className = name.replaceFirstChar { it.uppercase() } + "Dependency"
        try {
            return Class.forName("com.ch000se.templates.script.dependencies.$className")
        } catch (e: Exception) {
            throw DependencyNotFoundException(name)
        }
    }

    private fun getDependency(clazz: Class<*>): AbstractDependency {
        return clazz.getDeclaredConstructor().newInstance() as AbstractDependency
    }

}
