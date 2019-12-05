
import com.android.build.gradle.api.LibraryVariant
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import org.jetbrains.kotlin.gradle.internal.CacheImplementation

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("io.objectbox")
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

    libraryVariants.all(object : Action<LibraryVariant> {
        override fun execute(variant: LibraryVariant) {
            variant.compileConfiguration.resolutionStrategy {
                force(
                        Dep.Testing.espressoCore,
                        Dep.Testing.supportMonitor,
                        Dep.Testing.supportCore,
                        Dep.Support.recyclerView,
                        Dep.Support.v4,
                        Dep.Support.compatV7
                )
            }
            variant.runtimeConfiguration.resolutionStrategy {
                force(
                        Dep.Testing.espressoCore,
                        Dep.Testing.supportMonitor,
                        Dep.Testing.supportCore,
                        Dep.Support.recyclerView,
                        Dep.Support.v4,
                        Dep.Support.compatV7
                )
            }
        }

    })

    androidExtensions {
        configure(delegateClosureOf<AndroidExtensionsExtension> {
            isExperimental = true
            defaultCacheImplementation = CacheImplementation.SPARSE_ARRAY
        })
    }
}
dependencies {
    testImplementation(Dep.Testing.junit)
    androidTestImplementation(Dep.Testing.supportTestRule)
    androidTestImplementation(Dep.Testing.supportTestRunner)
    androidTestImplementation(Dep.Testing.espressoCore)
    androidTestImplementation(Dep.Testing.espressoContrib)
    androidTestImplementation(Dep.Testing.mockkAndroid)
    androidTestImplementation(Dep.Testing.supportCore)
    androidTestImplementation(Dep.Testing.supportMonitor)
    androidTestImplementation(Dep.Testing.assertJ)

    testImplementation(project(Dep.Modules.serverModel))
    testImplementation(Dep.ObjectBox.objectBoxWindows)
    testImplementation(Dep.Testing.supportTestRunner)
    testImplementation(Dep.Testing.supportJunitExt)
    testImplementation(Dep.Testing.supportTestRule)
    testImplementation(Dep.Testing.espressoCore)
    testImplementation(Dep.Testing.robolectric)
    testImplementation(Dep.Testing.supportCore)
    testImplementation(Dep.Testing.mockkUnit)
    testImplementation(Dep.Testing.assertJ)

    implementation(Dep.Kotlin.kotlinStd)

    implementation(Dep.Support.compatV7)
    implementation(Dep.Utils.timber)
    implementation(Dep.Moshi.kotshi)
    kapt(Dep.Moshi.kotshiCompiler)

    implementation(Dep.Retrofit.retrofit)

    implementation(Dep.RxJava.rxJava)
    implementation(Dep.RxJava.rxKotlin)
    implementation(Dep.RxJava.rxBindingKotlin)
    implementation(Dep.RxJava.rxBindingCompat)
    implementation(Dep.RxJava.rxBindingCompatKotlin)

    implementation(Dep.Moshi.moshi)
    implementation(Dep.Retrofit.okHttp)

    implementation(Dep.Dagger.injectAnnotation)
    implementation(Dep.Dagger.dagger)
    implementation(Dep.Dagger.daggerAndroid)

    implementation(Dep.Retrofit.okIO)

    kapt(Dep.Dagger.daggerCompiler)
    kapt(Dep.Dagger.daggerAndroidCompiler)

    implementation(Dep.ObjectBox.objectBoxJava)
    implementation(Dep.ObjectBox.objectBoxRxJava)
    implementation(Dep.ObjectBox.objectBoxKotlin)

    implementation(project(Dep.Modules.core))
    implementation(project(Dep.Modules.retrofit))
    implementation(project(Dep.Modules.base))
    implementation(project(Dep.Modules.serverModel))
    implementation(project(Dep.Modules.repository))
    implementation(project(Dep.Modules.navigation))
}