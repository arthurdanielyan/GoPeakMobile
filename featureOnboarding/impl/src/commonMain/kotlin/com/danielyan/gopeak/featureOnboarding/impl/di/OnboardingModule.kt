package com.danielyan.gopeak.featureOnboarding.impl.di

import com.danielyan.gopeak.featureOnboarding.api.OnboardingComponent
import com.danielyan.gopeak.featureOnboarding.impl.OnboardingComponentFactory
import org.koin.dsl.module

val onboardingModule = module {

    factory<OnboardingComponent.Factory> {
        OnboardingComponentFactory()
    }
}
