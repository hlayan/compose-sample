import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.konan.properties.loadProperties
import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    kotlin("android")
}

//val properties = gradleLocalProperties(rootDir)

//val RELEASE_KEYSTORE_PATH = properties.getProperty("RELEASE_KEYSTORE_PATH")
//    .toString()
//val RELEASE_KEYSTORE_PASSWORD = properties.getProperty("RELEASE_KEYSTORE_PASSWORD")
//    .toString()
//val RELEASE_KEY_ALIAS = properties.getProperty("RELEASE_KEY_ALIAS")
//    .toString()
//val RELEASE_KEY_PASSWORD = properties.getProperty("RELEASE_KEY_PASSWORD")
//    .toString()

android {
    compileSdk = 32
    buildToolsVersion = "32.0.0"
    namespace = "com.hlayan.mmkexchange"

    defaultConfig {
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0.0"
        applicationId = "com.hlayan.mmkexchange"
        vectorDrawables.useSupportLibrary = true
    }

    val releaseSigningConfig = signingConfigs.create("release") {
        storeFile = File("C:\\Users\\Hlayan Htet Aung\\.android\\debug.keystore")
        storePassword = "android"
        keyAlias = "AndroidDebugKey"
        keyPassword = "android"
    }

    println(releaseSigningConfig.toString())

    buildTypes {
        val proguardFile = getDefaultProguardFile("proguard-android-optimize.txt")
        val proguardRules = "proguard-rules.pro"
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            versionNameSuffix = "-debug"
            applicationIdSuffix = ".debug"
            proguardFiles(proguardFile, proguardRules)
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = releaseSigningConfig
            proguardFiles(proguardFile, proguardRules)
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
        buildConfig = false
    }

    composeOptions {
        useLiveLiterals = false
        kotlinCompilerExtensionVersion = "1.1.1"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")

    val composeVersion = "1.1.1"
    implementation("androidx.compose.ui:ui:1.2.0-beta02")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    implementation("androidx.compose.material3:material3:1.0.0-alpha12")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.activity:activity-compose:1.4.0")

    implementation("androidx.core:core-splashscreen:1.0.0-rc01")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.navigation:navigation-compose:2.5.0-rc01")

    implementation("com.google.android.material:material:1.6.0")
    implementation("com.google.accompanist:accompanist-pager:0.19.0")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.17.0")

    implementation("com.github.skydoves:sandwich:1.2.4")
    implementation("com.jakewharton.timber:timber:5.0.1")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
}