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

    sourceSets {
        val sharedTestDir = "src/sharedTest/java"
        getByName("test") {
            java.srcDirs(sharedTestDir)
        }
        getByName("androidTest") {
            java.srcDirs(sharedTestDir)
        }
    }
}

fun getReleaseDebuggable() =
        rootProject.extra["releaseDebuggable"] as Boolean

dependencies {
    testImplementation(Dep.Testing.junit)
    androidTestImplementation(Dep.Testing.supportTestRunner)
    androidTestImplementation(Dep.Testing.espressoCore)


    kaptTest(Dep.Dagger.daggerCompiler)
    kaptTest(Dep.Dagger.daggerAndroidCompiler)
    kaptAndroidTest(Dep.Dagger.daggerCompiler)
    kaptAndroidTest(Dep.Dagger.daggerAndroidCompiler)

    implementation(Dep.Kotlin.kotlinStd)

    implementation(Dep.Support.compatV7)
    implementation(Dep.Support.design)

    implementation(Dep.Retrofit.retrofit)
    implementation(Dep.RxJava.rxJava)
    implementation(Dep.RxJava.rxBinding)

    implementation(Dep.Utils.apacheCommons)


    implementation(Dep.Dagger.injectAnnotation)
    implementation(Dep.Dagger.dagger)
    implementation(Dep.Dagger.daggerAndroid)
    kapt(Dep.Dagger.daggerCompiler)
    kapt(Dep.Dagger.daggerAndroidCompiler)

    implementation(Dep.Firebase.firebaseMessaging)
    implementation(Dep.Firebase.firebaseCore)
    implementation(project(Dep.Modules.calendar))

}