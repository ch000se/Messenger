package com.ch000se.templates.script

import java.net.URI
import java.net.URL
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

interface TemplateFileSystem : AutoCloseable {

    fun getTemplates(): List<Path>

    fun openContent(path: Path): List<String>

    companion object {
        fun create(codeLocation: URL): TemplateFileSystem {
            return if (codeLocation.file.endsWith(".jar")) {
                // execution from Terminal
                JarTemplateFileSystem(codeLocation)
            } else {
                // execution from Android Studio
                IdeBuildTemplateFileSystem(codeLocation)
            }
        }
    }
}

private class JarTemplateFileSystem(
    private val codeLocation: URL,
) : TemplateFileSystem {

    private val fileSystem by lazy {
        val path = Paths.get(codeLocation.toURI())
        val fileUri = path.toUri()
        val jarUri = URI.create("jar:$fileUri")
        FileSystems.newFileSystem(jarUri, emptyMap<String, String>())
    }

    override fun getTemplates(): List<Path> {
        return Files.list(fileSystem.getPath("/templates")).toList()
    }

    override fun openContent(path: Path): List<String> {
        val fullPath = fileSystem.getPath("/").resolve(path)
        return Files.readAllLines(fullPath)
    }

    override fun close() {
        fileSystem.close()
    }
}

private class IdeBuildTemplateFileSystem(
    private val codeLocation: URL,
) : TemplateFileSystem {

    override fun getTemplates(): List<Path> {
        val path = Path.of(codeLocation.toURI())
            .parent.parent.parent
            .resolve("resources/main/templates")
        return Files.list(path).toList()
    }

    override fun openContent(path: Path): List<String> {
        return path.toFile().readLines()
    }

    override fun close() = Unit

}
