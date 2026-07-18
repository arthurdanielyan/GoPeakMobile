package com.danielyan.gopeak.root.impl

import com.danielyan.gopeak.decompose.AppComponentContext
import com.danielyan.gopeak.featureOnboarding.api.OnboardingComponent
import com.danielyan.gopeak.root.api.RootComponent

internal class RootComponentFactory(
    private val onboardingComponentFactory: OnboardingComponent.Factory,
) : RootComponent.Factory {

    override fun invoke(
        appComponentContext: AppComponentContext
    ): RootComponentImpl {
        return RootComponentImpl(
            appComponentContext = appComponentContext,
            onboardingComponentFactory = onboardingComponentFactory,
        )
    }
}