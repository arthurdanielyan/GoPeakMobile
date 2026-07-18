package com.danielyan.gopeak.root.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.danielyan.gopeak.designsystem.theme.GoPeakTheme
import com.danielyan.gopeak.featureOnboarding.api.OnboardingComponent
import com.danielyan.gopeak.featureOnboarding.ui.OnboardingScreen
import com.danielyan.gopeak.root.api.RootComponent

@Composable
fun RootScreen(
    component: RootComponent
) {
    Children(
        modifier = Modifier.fillMaxSize(),
        stack = component.childStack,
        animation = stackAnimation(slide())
    ) {
        when (val child = it.instance) {
            is OnboardingComponent -> OnboardingScreen(child)
            else -> NotYetImplemented()
        }
    }
}

@Composable
private fun NotYetImplemented() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GoPeakTheme.colors.background)
            .systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Not Yet Implemented",
            style = GoPeakTheme.typography.heading,
            color = GoPeakTheme.colors.onBackground,
        )
    }
}
