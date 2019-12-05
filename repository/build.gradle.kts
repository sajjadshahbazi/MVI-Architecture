import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    //    id ("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.jvm")
    id("java-library")
}

dependencies {
    implementation(Dep.Kotlin.kotlinStd)
    implementation(Dep.RxJava.rxJava)
}
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8

}
repositories {
    mavenCentral()
}
val compileKotlin: KotlinCompile by tasks
val compileTestKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions.jvmTarget = "1.8"
compileTestKotlin.kotlinOptions.jvmTarget = "1.8"

