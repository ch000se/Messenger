package com.ch000se.templates.script

import com.ch000se.templates.script.dependencies.base.DependencyRegistry
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.exists
import kotlin.io.path.isDirectory
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.name
import kotlin.io.path.readText
import kotlin.io.path.writeText

interface TemplateGenerator {
    fun generate(inputArgs: InputArgs)
}

class TemplateGeneratorImpl : TemplateGenerator {

    override fun generate(inputArgs: InputArgs) {
        val codeSource = this::class.java.protectionDomain.codeSource
        val codeLocation = codeSource.location
        TemplateFileSystem.create(codeLocation).use { templateFileSystem ->
            templateFileSystem.generateModulesFromTemplate(inputArgs)
        }
    }

    private fun TemplateFileSystem.generateModulesFromTemplate(inputArgs: InputArgs) {
        val availableTemplates = getTemplates()
        val templatePath = availableTemplates.firstOrNull { it.name == inputArgs.templateName }
        if (templatePath == null) {
            throw IllegalArgumentException("Template '${inputArgs.templateName}' does not exist.")
        }

        val subdirectories = templatePath.listDirectoryEntries().filter { it.isDirectory() }
        val moduleNames = if (subdirectories.size <= 1) {
            listOf(inputArgs.outputModuleName)
        } else {
            subdirectories.map { "${inputArgs.outputModuleName}:${it.name}" }
        }

        val configPath = templatePath.resolve("template-config.txt")

        moduleNames.forEach { moduleName ->
            val modulePath = convertModuleNameToPath(moduleName)
            if (modulePath.exists()) {
                throw IllegalArgumentException("Cannot create module at '$modulePath' because it already exists.")
            }
            modulePath.createDirectories()

            val subDirName = moduleName.substringAfterLast(":")
            val sourcePath = if (subdirectories.size <= 1) {
                templatePath.resolve("template-content")
            } else {
                templatePath.resolve(subDirName)
            }

            copyTemplateContents(
                sourcePath = sourcePath,
                targetPath = modulePath,
                inputArgs = inputArgs,
            )
            adjustSourceFiles(modulePath, inputArgs)
            createGitIgnore(modulePath)
        }
        applyConfig(configPath, inputArgs)
        updateSettingsGradle(moduleNames)
    }

    private fun convertModuleNameToPath(moduleName: String): Path {
        return moduleName.trimStart(':').replace(":", File.separator).let(::Path)
    }

    private fun copyTemplateContents(
        sourcePath: Path,
        targetPath: Path,
        inputArgs: InputArgs,
    ) {
        universalCopyFiles(
            sourcePath = sourcePath, targetPath = targetPath, onSuccess = { filePath ->
                replacePlaceholders(filePath, inputArgs)
            })
    }

    private fun replacePlaceholders(filePath: Path, inputArgs: InputArgs) {
        val content = filePath.readText().let { text ->
            inputArgs.replacePlaceholders(text)
        }
        val contentWithDependencies = if (filePath.name == "build.gradle.kts") {
            processDependencies(content, inputArgs.templateName, inputArgs.dependencies)
        } else {
            content
        }
        filePath.writeText(contentWithDependencies)
        if (filePath.name.contains("ModuleName")) {
            val newName = filePath.name.replace("ModuleName", inputArgs.camelCaseModuleName)
            val newPath = filePath.parent.resolve(newName)
            Files.move(filePath, newPath)
        }
    }

    private fun processDependencies(
        content: String,
        templateName: String,
        dependencyNames: Set<String>,
    ): String {
        val dependencies = DependencyRegistry.resolveDependencies(templateName, dependencyNames)
        val plugins = dependencies.joinToString("\n") {
            it.pluginsBlock
        }
        val configs = dependencies.joinToString("\n") {
            it.configurationBlock
        }
        val libraries = dependencies.joinToString("\n") {
            it.librariesBlock
        }
        return content
            .replace("    %PLUGINS_SECTION%", plugins)
            .replace("%CONFIGURATION_SECTION%", configs)
            .replace("    %LIBRARIES_SECTION%", libraries)
    }

    private fun adjustSourceFiles(modulePath: Path, inputArgs: InputArgs) {
        val sources = modulePath.resolve("src")
        val sourceRoots = sources.listDirectoryEntries().filter { it.isDirectory() }.flatMap {
            listOf(it.resolve("java"), it.resolve("kotlin"))
        }.filter { it.exists() && it.isDirectory() }
        val pkgPath = inputArgs.outputModulePackage.replace(".", File.separator)
        sourceRoots.forEach { root ->
            val packageDir = root.resolve(pkgPath)
            val topLevelPackageName = Path.of(pkgPath).first().name
            packageDir.createDirectories()
            Files.list(root).forEach {
                if (it.name != topLevelPackageName) {
                    Files.move(it, packageDir.resolve(it.name))
                }
            }
        }
    }

    private fun TemplateFileSystem.applyConfig(configPath: Path, inputArgs: InputArgs) {
        val configApplier = ConfigApplier(configPath, inputArgs, fileSystem = this)
        configApplier.execute()
    }

    private fun createGitIgnore(modulePath: Path) {
        val gitIgnorePath = modulePath.resolve(".gitignore")
        gitIgnorePath.writeText("/build")
    }

    private fun updateSettingsGradle(modulePaths: List<String>) {
        val settingsFile = File("settings.gradle.kts").takeIf { it.exists() }
            ?: File("settings.gradle").takeIf { it.exists() }
            ?: throw IllegalStateException("Could not find settings.gradle or settings.gradle.kts in the project root.")

        val includeStatements = modulePaths.joinToString("\n") { "include(\"$it\")" }
        settingsFile.appendText("\n$includeStatements")
    }

}
