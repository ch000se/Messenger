import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    `kotlin-dsl`
}

dependencies {
    //  доступ до libs через костиль
    implementation(files(LibrariesForLibs::class.java.protectionDomain.codeSource.location))

    implementation(libs.plugin.android.application)
    implementation(libs.plugin.android.library)
    implementation(libs.plugin.kotlin.android)
    implementation(libs.plugin.kotlin.compose)
    implementation(libs.plugin.kotlin.jvm)
    implementation(libs.plugin.hilt)
    implementation(libs.plugin.ksp)
}