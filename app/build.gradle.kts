import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import org.jetbrains.kotlin.gradle.internal.CacheImplementation

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {

    compileSdkVersion(androidCompileSdkVersion)
    buildToolsVersion(androidBuildToolsVersion)

    defaultConfig {
        applicationId = "com.example.signupuser"
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

    testImplementation(Dep.Testing.junit)
    androidTestImplementation(Dep.Testing.supportTestRunner)
    androidTestImplementation(Dep.Testing.espressoCore)
    androidTestImplementation(Dep.Testing.supportTestRule)
    androidTestImplementation(Dep.Testing.espressoCore)
    androidTestImplementation(Dep.Testing.espressoContrib)
    androidTestImplementation(Dep.Testing.mockkAndroid)
    androidTestImplementation(Dep.Testing.assertJ)

    implementation(Dep.Kotlin.kotlinStd)

    implementation(Dep.Support.compatV7)
    implementation(Dep.Support.design)
    implementation(Dep.Support.cardView)
    implementation(Dep.Support.recyclerView)
    implementation(Dep.Support.constraintLayout)

    implementation(Dep.Utils.timber)
    implementation(Dep.Utils.glide)

    implementation(Dep.Ui.roundCardView)
    implementation(Dep.Ui.roundImageVIew)
    implementation(Dep.Ui.discreteScrollView)

    implementation(Dep.Google.maps)

    implementation(Dep.Retrofit.retrofit)
    implementation(Dep.Moshi.kotshi)
    kapt(Dep.Moshi.kotshiCompiler)
    implementation(Dep.Moshi.kotshi)

    implementation(Dep.RxJava.rxJava)
    implementation(Dep.RxJava.rxKotlin)
    implementation(Dep.RxJava.rxBindingKotlin)
    implementation(Dep.RxJava.rxBindingCompat)
    implementation(Dep.RxJava.rxBindingCompatKotlin)

    implementation(Dep.Moshi.moshi)
    implementation(Dep.Retrofit.okHttp)
    implementation(Dep.Retrofit.okHttpLogging)

//    implementation(Dep.callenderUtils.persianDatePicker)


    implementation(Dep.Dagger.injectAnnotation)
    implementation(Dep.Dagger.dagger)
    implementation(Dep.Dagger.daggerAndroid)
    kapt(Dep.Dagger.daggerCompiler)
    kapt(Dep.Dagger.daggerAndroidCompiler)

    implementation(Dep.Moshi.moshiRetrofit)
    implementation(Dep.RxJava.rxJavaRetrofit)
    implementation(project(Dep.Modules.retrofit))
    implementation(project(Dep.Modules.core))
    implementation(project(Dep.Modules.base))
    implementation(project(Dep.Modules.widgets))
    implementation(project(Dep.Modules.serverModel))
    implementation(project(Dep.Modules.resources))
    implementation(project(Dep.Modules.repositories))
    implementation(project(Dep.Modules.repository))
    implementation(project(Dep.Modules.calendar))
    implementation(project(Dep.Modules.navigation))

    implementation(Dep.androidKTS.coreExt)

    implementation(Dep.Support.compatV7)
    implementation(Dep.Utils.timber)
    implementation(Dep.Moshi.kotshi)
    implementation(Dep.Moshi.moshi)

    implementation(Dep.Utils.epoxy)
    kapt(Dep.Utils.epoxyCompiler)
}

