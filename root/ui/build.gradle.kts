plugins {
    alias(libs.plugins.goPeakAndroidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "com.danielyan.gopeak.root.ui"
}

dependencies {
    api(projects.root.api)

    implementation(libs.decompose.extensions.compose)
    implementation(projects.core.designSystem)
    implementation(projects.core.decompose)

    implementation(projects.featureOnboarding.ui)
}

composeCompiler {
    stabilityConfigurationFiles.add(
        layout.projectDirectory.file("compose_stability.conf")
    )
}
