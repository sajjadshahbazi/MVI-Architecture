import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import org.jetbrains.kotlin.gradle.internal.CacheImplementation
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt


plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-kapt")
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
}
dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    testImplementation(Dep.Testing.junit)
    androidTestImplementation(Dep.Testing.supportTestRunner)
    androidTestImplementation(Dep.Testing.espressoCore)

    implementation(Dep.Kotlin.kotlinStd)

    implementation(Dep.Support.compatV7)
    implementation(Dep.Support.design)
    implementation(Dep.Support.constraintLayout)

    implementation(Dep.ArchitectureComp.lifecycleExtension)

    implementation(Dep.Dagger.dagger)
    implementation(Dep.Dagger.daggerAndroid)
    kapt(Dep.Dagger.daggerCompiler)

    implementation(Dep.Moshi.moshi)
    implementation(Dep.Moshi.kotshi)
    kapt(Dep.Moshi.kotshiCompiler)

    implementation(Dep.RxJava.rxJava)
    implementation(Dep.Ui.lottie)
    implementation(Dep.Ui.alerter)
    api(Dep.Ui.tSnackbar)

    implementation(project(Dep.Modules.retrofit))
    implementation(project(Dep.Modules.navigation))
    implementation(project(Dep.Modules.resources))
}