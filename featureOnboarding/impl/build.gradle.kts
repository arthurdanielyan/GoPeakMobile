import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
}

kotlin {
    iosArm64()
    iosSimulatorArm64()

    androidLibrary {
        namespace = "com.danielyan.gopeak.featureOnboarding.impl"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.featureOnboarding.api)
            implementation(projects.core.decompose)

            // Used by commonMain DI (di/OnboardingModule.kt), so it must live in commonMain
            // for the iOS targets to resolve it as well.
            implementation(libs.koin.core)
        }
    }
}
