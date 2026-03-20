plugins {
    alias(libs.plugins.custom.kotlin.script)
}

kotlinScript {
    mainClass.set("com.ch000se.templates.script.MainKt")
    archiveName.set("create")
}

tasks.register<Copy>("copyJarToRoot") {
    dependsOn(tasks.jar)
    from(layout.buildDirectory.dir("libs")) {
        include("*.jar")
    }
    into(rootProject.projectDir)
    doNotTrackState("Copying to project root directory")
}

dependencies {

}
