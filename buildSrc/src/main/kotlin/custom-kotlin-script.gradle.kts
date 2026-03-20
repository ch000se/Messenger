plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

val fatJar = extensions.create<CustomKotlinScriptExtension>("kotlinScript")

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(configurations.runtimeClasspath.get().map {
        if (it.isDirectory) it else zipTree(it)
    })

    archiveBaseName.set(fatJar.archiveName)

    doFirst {
        manifest {
            attributes("Main-Class" to fatJar.mainClass.get())
        }
    }
}