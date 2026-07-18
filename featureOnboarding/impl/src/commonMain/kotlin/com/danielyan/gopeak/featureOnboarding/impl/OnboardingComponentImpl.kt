package com.danielyan.gopeak.featureOnboarding.impl

import com.danielyan.gopeak.decompose.AppComponentContext
import com.danielyan.gopeak.decompose.asValue
import com.danielyan.gopeak.featureOnboarding.api.OnboardingComponent
import com.danielyan.gopeak.featureOnboarding.api.OnboardingUiCallbacks
import com.danielyan.gopeak.featureOnboarding.api.OnboardingViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

internal class OnboardingComponentImpl(
    appComponentContext: AppComponentContext
) : OnboardingComponent,
    OnboardingUiCallbacks,
    AppComponentContext by appComponentContext {

    private companion object {
        const val OnboardingPageCount = 5
    }

    private val currentPageIndex = MutableStateFlow(0)
    private val _uiState = currentPageIndex.mapLatest { currentPageIndex ->
        OnboardingViewState(
            currentPageIndex = currentPageIndex,
        )
    }

    override val uiState = _uiState.asValue(
        initialValue = OnboardingViewState(),
        scope = componentScope,
    )

    override val uiCallbacks = this

    override fun onContinueClick() {
        if (currentPageIndex.value < OnboardingPageCount - 1) {
            currentPageIndex.update { it + 1 }
        }
    }

    override fun onBackClick() {
        currentPageIndex.update { (it - 1).coerceAtLeast(0) }
    }
}
