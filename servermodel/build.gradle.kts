
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
            isDebuggable = isReleaseDebuggable
            isMinifyEnabled = isReleaseMinify
            multiDexEnabled = isReleaseMultiDex
            proguardFiles(getDefaultProguardFile("proguard-android.txt"),
                    rootProject.extra["proguardFileAddress"])
        }
        getByName("debug") {
            isDebuggable = isDebugDebuggable
            isMinifyEnabled = isDebugMinify
            multiDexEnabled = isDebugMultiDex
            proguardFiles(getDefaultProguardFile("proguard-android.txt"),
                    rootProject.extra["proguardFileAddress"])
        }
    }

    androidExtensions {
        configure(delegateClosureOf<AndroidExtensionsExtension> {
            isExperimental = true
            defaultCacheImplementation = CacheImplementation.SPARSE_ARRAY
        })
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(Dep.Kotlin.kotlinStd)
    implementation(Dep.RxJava.rxJava)
    implementation(Dep.Moshi.kotshi)
//    implementation(Dep.Moshi.moshiAdapters)
    implementation(Dep.Retrofit.okIO)
    implementation(Dep.Moshi.moshi)
    kapt(Dep.Moshi.kotshiCompiler)
    implementation(Dep.Moshi.kotshi)
    implementation(Dep.Testing.junit)
    implementation(Dep.Testing.assertJ)

}