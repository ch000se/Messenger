plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    compileSdk = Const.COMPILE_SDK

    defaultConfig {
        minSdk = Const.MIN_SDK
        targetSdk = Const.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

setupCompose()

//  доступ до libs
//
//val material3Dependency = library("androidx-material3")
//
//dependencies {
//    implementation(material3Dependency)
//}

//  доступ до libs через костиль
//val customLibs = the<LibrariesForLibs>()
//dependencies {
//    implementation(customLibs.androidx.material3)
//}
