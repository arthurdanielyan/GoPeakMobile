package com.danielyan.gopeak.featureOnboarding.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.danielyan.gopeak.designsystem.theme.GoPeakTheme
import com.danielyan.gopeak.featureOnboarding.api.OnboardingComponent
import com.danielyan.gopeak.featureOnboarding.api.OnboardingUiCallbacks
import com.danielyan.gopeak.featureOnboarding.api.OnboardingViewState

@Composable
fun OnboardingScreen(
    component: OnboardingComponent
) {
    val uiState by component.uiState.subscribeAsState()

    OnboardingScreen(
        state = uiState,
        callbacks = component.uiCallbacks,
    )
}

@Composable
private fun OnboardingScreen(
    state: OnboardingViewState,
    callbacks: OnboardingUiCallbacks,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GoPeakTheme.colors.background)
            .clickable(onClick = callbacks::onContinueClick)
            .systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Onboarding ${state.currentPageIndex + 1}",
            style = GoPeakTheme.typography.heading,
            color = GoPeakTheme.colors.onBackground,
        )
    }
}

@PreviewLightDark
@Composable
private fun OnboardingScreenPreview() {
    GoPeakTheme {
        OnboardingScreen(
            state = OnboardingViewState(),
            callbacks = object : OnboardingUiCallbacks {
                override fun onContinueClick() = Unit
                override fun onBackClick() = Unit
            }
        )
    }
}
