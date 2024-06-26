@file:Suppress("UnstableApiUsage")
import org.jetbrains.compose.ExperimentalComposeLibrary


plugins {
    id("com.android.application")
    alias(libs.plugins.kover)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.ksp)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.buildkonfig.plugin)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.koin.android)
            implementation(libs.sql.delight.android)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(libs.kamel)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            implementation(libs.ktor.logging)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.tabNavigator)
            implementation(libs.moko.core)
            implementation(libs.moko.mvvm.compose)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.annotation)
            //implementation(libs.sql.delight)
            implementation(libs.sql.delight.coroutines)
            implementation(libs.supabase.ktx)
            implementation(libs.supabase.compose.auth.ui)
            implementation(libs.supabase.compose.auth)
            implementation(libs.voyager.navigator)
            implementation(libs.rinku.core)
            implementation(libs.rinku.compose.ext)
            implementation(libs.bundles.koin.impl)
            implementation(projects.platform)
            implementation(projects.designsystem)
            implementation(projects.data)
            implementation(projects.domain)

            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.bundles.koin.impl)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sql.delight.ios)
        }
    }
}

android {
    namespace = "org.rudi.learnkmp1"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "org.rudi.learnkmp1"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

