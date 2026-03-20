package com.ch000se.templates.script

import org.intellij.lang.annotations.Language
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.exists

class ConfigApplier(
    val configPath: Path,
    val inputArgs: InputArgs,
    val fileSystem: TemplateFileSystem,
) {

    fun execute() {
        if (!configPath.exists()) return

        val lines = fileSystem.openContent(configPath)
        var currentFilePath: Path? = null
        var currentLocation: String? = null
        val contentBuffer = mutableListOf<String>()
        val filesContent = mutableMapOf<Path, String>()

        fun flushBuffer() {
            if (currentFilePath != null && currentLocation != null && contentBuffer.isNotEmpty()) {
                val file = requireNotNull(currentFilePath)
                val contentToInsert =
                    inputArgs.replacePlaceholders(contentBuffer.joinToString("\n"))
                val originalText = filesContent[file] ?: if (file.toFile().exists()) {
                    file.toFile().readText()
                } else {
                    ""
                }
                val updatedText = when {
                    currentLocation == "bottom" -> {
                        originalText + "\n" + contentToInsert.trimIndent() + "\n"
                    }

                    currentLocation == "imports" -> {
                        updateAfterRegexp(
                            originContent = originalText,
                            contentToInsert = contentToInsert.trimIndent(),
                            findLast = true,
                            "^import .+$", "^package .+$",
                        )
                    }

                    currentLocation == "dependencies" -> {
                        updateAfterRegexp(
                            originContent = originalText,
                            contentToInsert = contentToInsert,
                            findLast = false,
                            "dependencies\\s*\\{",
                        )
                    }

                    currentLocation!!.startsWith("function ") -> {
                        val funcName = currentLocation!!.removePrefix("function ").trim()
                        updateAfterRegexp(
                            originContent = originalText,
                            contentToInsert = contentToInsert,
                            findLast = false,
                            "fun\\s+$funcName.*?\\{",
                        )
                    }

                    currentLocation!!.startsWith("class ") -> {
                        val className = currentLocation!!.removePrefix("class ").trim()
                        updateAfterRegexp(
                            originContent = originalText,
                            contentToInsert = "\n$contentToInsert",
                            findLast = false,
                            "(class|object|interface)\\s+$className.*?\\{",
                        )
                    }

                    else -> originalText
                }
                filesContent[file] = updatedText
            }
            contentBuffer.clear()
            currentLocation = null
        }

        for (line in lines) {
            when {
                line.startsWith(".file ") -> {
                    flushBuffer()
                    val relPath = line.removePrefix(".file ").trim().let {
                        inputArgs.replacePlaceholders(it)
                    }
                    currentFilePath = Path(relPath)
                    if (!currentFilePath.toFile().exists()) {
                        currentFilePath.toFile().parentFile?.mkdirs()
                        currentFilePath.toFile().createNewFile()
                    }
                }

                line.startsWith(".addTo ") -> {
                    flushBuffer()
                    currentLocation = line.removePrefix(".addTo ").trim()
                }

                line.trim() == ".end" -> flushBuffer()
                currentLocation != null -> contentBuffer.add(line)
            }
        }
        flushBuffer()
        for ((file, text) in filesContent) {
            file.toFile().writeText(text)
        }
    }

    private fun updateAfterRegexp(
        originContent: String,
        contentToInsert: String,
        findLast: Boolean = false,
        @Language("RegExp")
        vararg patterns: String,
    ): String {
        for (pattern in patterns) {
            val regex = Regex(pattern, RegexOption.MULTILINE)
            val match = if (findLast) {
                regex.findAll(originContent).lastOrNull()
            } else {
                regex.find(originContent)
            }
            if (match != null) {
                val idx = match.range.last + 1
                return originContent.substring(
                    0,
                    idx
                ) + "\n" + contentToInsert + originContent.substring(idx)
            }
        }
        return originContent
    }
}
