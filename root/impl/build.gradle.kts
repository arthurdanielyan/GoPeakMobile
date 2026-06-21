import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    iosArm64()
    iosSimulatorArm64()

    androidLibrary {
        namespace = "com.danielyan.gopeak.root.impl"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.root.api)
            implementation(projects.core.decompose)

            // Used by commonMain DI (di/RootModule.kt), so it must live in commonMain
            // for the iOS targets to resolve it as well.
            implementation(libs.koin.core)

            // The Compose compiler plugin runs on every target (incl. iOS) and requires
            // the Compose runtime on the classpath even though only androidMain has UI.
            implementation(libs.compose.runtime)
        }

        androidMain.dependencies {
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.decompose.extensions.compose)
        }
    }
}

composeCompiler {
    stabilityConfigurationFiles.add(
        layout.projectDirectory.file("compose_stability.conf")
    )
}
