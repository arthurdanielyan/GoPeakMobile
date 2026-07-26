package com.danielyan.gopeak.featureOnboarding.api

import com.danielyan.gopeak.decompose.AppComponentContext

interface OnboardingComponent {

    companion object {
        const val OnboardingPageCount = 5
    }

    val uiCallbacks: OnboardingUiCallbacks

    fun interface Factory {
        operator fun invoke(
            appComponentContext: AppComponentContext,
        ): OnboardingComponent
    }
}