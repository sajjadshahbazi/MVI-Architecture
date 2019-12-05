import org.jetbrains.kotlin.config.KotlinCompilerVersion
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import org.jetbrains.kotlin.gradle.internal.CacheImplementation

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}
//
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
    implementation(Dep.Support.cardView)
    implementation(Dep.Support.recyclerView)
    implementation(Dep.Support.constraintLayout)

    implementation(Dep.RxJava.rxJava)
    implementation(Dep.RxJava.rxKotlin)
    implementation(Dep.RxJava.rxBinding)
    implementation(Dep.RxJava.rxBindingKotlin)
    implementation(Dep.RxJava.rxBindingCompat)
    implementation(Dep.RxJava.rxBindingCompatKotlin)

    implementation(project(Dep.Modules.core))
    implementation(project(Dep.Modules.base))
    implementation(project(Dep.Modules.resources))
}
