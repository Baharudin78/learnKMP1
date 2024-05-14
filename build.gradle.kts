@file:Suppress("UnstableApiUsage")
plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.ktlint)
    alias(libs.plugins.kover) apply false
    alias(libs.plugins.moko.plugin) apply false
    alias(libs.plugins.buildkonfig.plugin) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.jetbrainsKotlinJvm) apply false
}

buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath(libs.gradle)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.google.services)
        classpath(libs.firebase.crashlytics.gradle)
        classpath(libs.moko.resources.generator)
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}