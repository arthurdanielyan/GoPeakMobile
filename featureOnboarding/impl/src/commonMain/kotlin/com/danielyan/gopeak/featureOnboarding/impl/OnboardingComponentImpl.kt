package com.danielyan.gopeak.featureOnboarding.impl

import com.danielyan.gopeak.decompose.AppComponentContext
import com.danielyan.gopeak.featureOnboarding.api.OnboardingComponent
import com.danielyan.gopeak.featureOnboarding.api.OnboardingUiCallbacks

internal class OnboardingComponentImpl(
    appComponentContext: AppComponentContext
) : OnboardingComponent,
    OnboardingUiCallbacks,
    AppComponentContext by appComponentContext {

    override val uiCallbacks = this

    override fun onFinish() {
        // TODO: Not Yet Implemented
    }

    override fun onSkipClick() {
        // TODO: Not Yet Implemented
    }
}
