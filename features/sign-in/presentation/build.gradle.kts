plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose)
    // alias(libs.plugins.roborazzi) // TODO: Re-enable when compatible with AGP 9.x
}

android {
    namespace = "com.ch000se.messenger.feature.signin.presentation"
    resourcePrefix = "sign_in_"
}

setupCompose()
// setupScreenshotTests() // TODO: Re-enable when roborazzi is compatible with AGP 9.x

dependencies {
    api(projects.features.signIn.domain)
    implementation(projects.core.essentials)
    implementation(projects.core.theme)
    implementation(projects.core.navigationDsl)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}
