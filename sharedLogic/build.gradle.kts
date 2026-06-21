import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "SharedLogic"
            isStatic = true

            // Exported so the generated Obj-C/Swift header exposes the root component,
            // its screen configs and the Decompose Value/ChildStack types to SwiftUI.
            export(projects.root.api)
            export(projects.core.decompose)
            export(libs.decompose.core)
        }
    }
    
    androidLibrary {
       namespace = "com.danielyan.gopeak.sharedLogic"
       compileSdk = libs.versions.android.compileSdk.get().toInt()
       minSdk = libs.versions.android.minSdk.get().toInt()
    
       compilerOptions {
           jvmTarget = JvmTarget.JVM_17
       }
       androidResources {
           enable = true
       }
       withHostTest {
           isIncludeAndroidResources = true
       }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(projects.root.impl)

            // `api` (not `implementation`) so these can be `export`ed to the iOS framework.
            api(projects.root.api)
            api(projects.core.decompose)
            api(libs.decompose.core)
        }
    }
}