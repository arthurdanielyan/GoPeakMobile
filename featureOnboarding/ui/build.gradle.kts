plugins {
    alias(libs.plugins.goPeakAndroidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "com.danielyan.gopeak.featureOnboarding.ui"
}

dependencies {
    api(projects.featureOnboarding.api)

    implementation(projects.core.designSystem)

    // OnboardingComponent.uiState exposes Decompose's Value, which featureOnboarding:api
    // only depends on with `implementation`, so pull it in directly (+ subscribeAsState).
    implementation(projects.core.decompose)
    implementation(libs.decompose.extensions.compose)
}

composeCompiler {
    stabilityConfigurationFiles.add(
        layout.projectDirectory.file("compose_stability.conf")
    )
}
