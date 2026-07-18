package com.danielyan.gopeak.root.impl

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import com.danielyan.gopeak.decompose.AppComponentContext
import com.danielyan.gopeak.decompose.appChildStack
import com.danielyan.gopeak.featureOnboarding.api.OnboardingComponent
import com.danielyan.gopeak.root.api.RootComponent
import com.danielyan.gopeak.root.api.RootScreenConfig
import com.danielyan.gopeak.root.api.RootViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class RootComponentImpl(
    appComponentContext: AppComponentContext,
    private val onboardingComponentFactory: OnboardingComponent.Factory,
) : RootComponent, AppComponentContext by appComponentContext {

    private val showSplash = MutableStateFlow(true)
    override val uiState = showSplash.mapLatest { showSplash ->
        RootViewState(
            showSplash = showSplash
        )
    }.stateIn(componentScope, SharingStarted.Eagerly, RootViewState())

    private val stackNavigation = StackNavigation<RootScreenConfig>()

    override val childStack = appChildStack(
        source = stackNavigation,
        initialConfiguration = RootScreenConfig.OnboardingConfig,
        serializer = RootScreenConfig.serializer(),
        childFactory = { config, childComponentContext ->
            when (config) {
                RootScreenConfig.OnboardingConfig ->
                    onboardingComponentFactory(childComponentContext)

                RootScreenConfig.LoginConfig -> Any()
            }
        }
    )

    init {
        componentScope.launch {
            showSplash.update { false }
        }
    }

    override fun onBack() {
        stackNavigation.pop()
    }
}