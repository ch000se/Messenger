plugins {
    alias(libs.plugins.custom.kotlin.library)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(projects.core.essentials)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
}
