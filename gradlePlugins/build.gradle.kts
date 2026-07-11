import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.jvm)
    `java-gradle-plugin`
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create("goPeakAndroidLibraryPlugin") {
            id = "goPeakAndroidLibraryPlugin"
            implementationClass = "com.solit.gradleplugins.GoPeakAndroidLibraryPlugin"
            version = "1.0.0"
        }
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())

    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.android.tools.build.gradle)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.compilerOptions {
    jvmTarget.set(JvmTarget.JVM_17)
}
