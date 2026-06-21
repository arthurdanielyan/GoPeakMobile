package com.danielyan.gopeak.root.api

import com.danielyan.gopeak.decompose.ScreenConfig
import kotlinx.serialization.Serializable

@Serializable
sealed interface RootScreenConfig : ScreenConfig {

    @Serializable
    data object OnboardingConfig : RootScreenConfig

    @Serializable
    data object LoginConfig : RootScreenConfig
}