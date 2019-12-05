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
    testImplementation(Dep.Testing.jsonTest)
    androidTestImplementation(Dep.Testing.supportTestRunner)
    androidTestImplementation(Dep.Testing.espressoCore)

    implementation(Dep.Kotlin.kotlinStd)
    implementation(Dep.Support.annotation)

    implementation(Dep.Utils.timber)

    implementation(Dep.Moshi.moshi)
    implementation(Dep.RxJava.rxJava)
    implementation(Dep.Retrofit.retrofit)
    implementation(project(Dep.Modules.resources))
}