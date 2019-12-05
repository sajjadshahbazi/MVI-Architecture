plugins {
    id("com.android.library")
    kotlin("android")
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

}
dependencies {
    testImplementation(Dep.Testing.junit)
    androidTestImplementation(Dep.Testing.supportTestRunner)
    androidTestImplementation(Dep.Testing.espressoCore)

    implementation(Dep.Support.compatV7)
    implementation(Dep.Support.design)

    implementation(Dep.Kotlin.kotlinStd)
}