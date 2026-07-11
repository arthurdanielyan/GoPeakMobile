package com.solit.gradleplugins

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

class GoPeakAndroidLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        applyPlugins(target)
        setProjectConfig(target)
    }

    private fun applyPlugins(project: Project) {
        val libs = project.versionCatalog

        project.pluginManager.apply {
            apply(libs.getPlugin("android-library"))
        }
    }

    private fun setProjectConfig(project: Project) {
        project.android {
            compileSdk {
                version = release(project.versionCatalog.getVersion("android-compileSdk").toInt())
            }

            defaultConfig {
                minSdk = project.versionCatalog.getVersion("android-minSdk").toInt()
            }

            buildTypes {
                release {
                    // Let the application module perform the actual shrinking/obfuscation.
                    // Library modules only contribute consumer rules.
                    isMinifyEnabled = false

                    val consumerFile = project.file("consumer-rules.pro")
                    if (consumerFile.exists()) {
                        // Expose rules to consumers of this library (the app),
                        // but do NOT run R8 on the library itself.
                        consumerProguardFiles(consumerFile)
                    }
                }
                debug {
                    isMinifyEnabled = false
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
        }

        project.kotlinExtension.jvmToolchain(17)
    }
}