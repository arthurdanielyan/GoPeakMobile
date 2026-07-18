package com.danielyan.gopeak.featureOnboarding.api

import com.arkivanov.decompose.value.Value
import com.danielyan.gopeak.decompose.AppComponentContext

interface OnboardingComponent {

    val uiState: Value<OnboardingViewState>
    val uiCallbacks: OnboardingUiCallbacks

    fun interface Factory {
        operator fun invoke(
            appComponentContext: AppComponentContext,
        ): OnboardingComponent
    }
}