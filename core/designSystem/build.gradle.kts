plugins {
    alias(libs.plugins.goPeakAndroidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "com.danielyan.gopeak.designsystem"
}

dependencies {
    api(libs.compose.runtime)
    api(libs.compose.foundation)
    api(libs.compose.material3)
    api(libs.compose.ui)
    api(libs.compose.uiToolingPreview)
    debugApi(libs.compose.uiTooling)
}

