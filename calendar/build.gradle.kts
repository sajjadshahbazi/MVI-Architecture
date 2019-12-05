import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import org.jetbrains.kotlin.gradle.internal.CacheImplementation

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(androidCompileSdkVersion)
    buildToolsVersion(androidBuildToolsVersion)

    defaultConfig {
        minSdkVersion(androidMinSdkVersion)
        targetSdkVersion(androidTargetSdkVersion)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    androidExtensions {
        configure(delegateClosureOf<AndroidExtensionsExtension> {
            isExperimental = true
            defaultCacheImplementation = CacheImplementation.SPARSE_ARRAY
        })
    }
}
dependencies {
    testImplementation(Dep.Testing.junit)
    androidTestImplementation(Dep.Testing.supportTestRunner)
    androidTestImplementation(Dep.Testing.espressoCore)
    androidTestImplementation(Dep.Testing.mockkAndroid)
    testImplementation(Dep.Testing.mockkUnit)
    testImplementation(Dep.Testing.assertJ)
    androidTestImplementation(Dep.Testing.assertJ)

    implementation(Dep.Kotlin.kotlinStd)

    implementation(Dep.Support.compatV7)
}