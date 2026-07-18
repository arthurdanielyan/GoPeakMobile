package com.danielyan.gopeak.featureOnboarding.impl

import com.danielyan.gopeak.decompose.AppComponentContext
import com.danielyan.gopeak.featureOnboarding.api.OnboardingComponent

internal class OnboardingComponentFactory : OnboardingComponent.Factory {

    override fun invoke(
        appComponentContext: AppComponentContext
    ): OnboardingComponentImpl {
        return OnboardingComponentImpl(
            appComponentContext = appComponentContext,
        )
    }
}
