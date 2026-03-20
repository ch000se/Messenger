package com.ch000se.templates.script.dependencies.base

class DependencyNotFoundException(
    name: String
) : Exception("Dependency '$name' does not exist.")
