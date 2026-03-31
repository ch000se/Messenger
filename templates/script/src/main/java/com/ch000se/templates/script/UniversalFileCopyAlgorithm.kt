package com.ch000se.templates.script

import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.StandardCopyOption
import java.nio.file.attribute.BasicFileAttributes
import kotlin.io.path.name
import kotlin.io.path.relativeTo

/**
 * This function can copy files across different File Systems.
 */
fun universalCopyFiles(
    sourcePath: Path,
    targetPath: Path,
    onSuccess: (Path) -> Unit,
) {
    Files.walkFileTree(sourcePath, object : SimpleFileVisitor<Path>() {
        override fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult {
            val relativeDir = dir.relativeTo(sourcePath)
            if (relativeDir.name.isNotEmpty()) {
                val targetNewDir = targetPath.resolve(relativeDir.toString())
                Files.createDirectories(targetNewDir)
            }
            return FileVisitResult.CONTINUE
        }

        override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
            val relativeFile = file.relativeTo(sourcePath)
            val targetFileName = if (relativeFile.toString().endsWith(".template")) {
                relativeFile.toString().removeSuffix(".template")
            } else {
                relativeFile.toString()
            }
            val targetNewFile = targetPath.resolve(targetFileName)
            Files.copy(file, targetNewFile, StandardCopyOption.REPLACE_EXISTING)
            onSuccess(targetNewFile)
            return FileVisitResult.CONTINUE
        }
    })

}