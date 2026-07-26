package com.danielyan.gopeak.featureOnboarding.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.danielyan.gopeak.designsystem.components.ButtonIcon
import com.danielyan.gopeak.designsystem.components.PrimaryButton
import com.danielyan.gopeak.designsystem.components.StepIndicator
import com.danielyan.gopeak.designsystem.components.TertiaryButton
import com.danielyan.gopeak.designsystem.components.topBar.TopBarWithLogo
import com.danielyan.gopeak.designsystem.ext.modifyIf
import com.danielyan.gopeak.designsystem.ext.noSemantics
import com.danielyan.gopeak.designsystem.theme.GoPeakTheme
import com.danielyan.gopeak.featureOnboarding.api.OnboardingComponent
import com.danielyan.gopeak.featureOnboarding.api.OnboardingComponent.Companion.OnboardingPageCount
import com.danielyan.gopeak.featureOnboarding.api.OnboardingUiCallbacks
import kotlinx.coroutines.launch
import com.danielyan.gopeak.designsystem.R.drawable as DesignSystemDrawables
import com.danielyan.gopeak.featureOnboarding.ui.R.string as Strings

@Composable
fun OnboardingScreen(
    component: OnboardingComponent
) {
    OnboardingScreen(
        callbacks = component.uiCallbacks,
    )
}

@Composable
private fun OnboardingScreen(
    callbacks: OnboardingUiCallbacks,
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { OnboardingPageCount }
    )
    val scope = rememberCoroutineScope()
    val toNextPage = remember {
        {
            scope.launch {
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1).coerceAtMost(OnboardingPageCount - 1)
                )
            }
            Unit
        }
    }
    val toPreviousPage = remember {
        {
            scope.launch {
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage - 1).coerceAtLeast(0)
                )
            }
            Unit
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GoPeakTheme.colors.background)
            .systemBarsPadding()
    ) {
        TopBarWithLogo(
            modifier = Modifier.fillMaxWidth(),
            trailingContent = {
                val showSkipButton by remember {
                    derivedStateOf { pagerState.currentPage > 0 }
                }
                val skipButtonAlpha by animateFloatAsState(
                    targetValue = if (showSkipButton) 1f else 0f
                )
                TertiaryButton(
                    modifier = Modifier
                        .graphicsLayer {
                            alpha = skipButtonAlpha
                        }
                        .modifyIf(showSkipButton.not()) {
                            noSemantics()
                        },
                    enabled = showSkipButton,
                    text = stringResource(Strings.onboarding_skip),
                    onClick = callbacks::onSkipClick
                )
            }
        )
        HorizontalPager(
            modifier = Modifier
                .weight(1f),
            state = pagerState,
            userScrollEnabled = false,
        ) { index ->
            when (index) {
                0 -> OnboardingPage1(
                    modifier = Modifier.fillMaxSize(),
                    onGetStartedClick = toNextPage,
                    onSkipClick = callbacks::onSkipClick,
                )

                else -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(GoPeakTheme.colors.background)
                            .systemBarsPadding(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Onboarding ${index + 1}",
                            style = GoPeakTheme.typography.heading,
                            color = GoPeakTheme.colors.onBackground,
                        )
                    }
                }
            }
        }
        HorizontalDivider(
            color = GoPeakTheme.colors.outline
        )
        OnboardingNavigation(
            modifier = Modifier.fillMaxWidth(),
            showBackButton = pagerState.currentPage > 0,
            showContinueButton = pagerState.currentPage > 0,
            onContinueClick = toNextPage,
            onBackClick = toPreviousPage,
            currentPage = pagerState.currentPage,
            totalPages = OnboardingPageCount,
        )
    }
}

@Composable
private fun OnboardingNavigation(
    modifier: Modifier = Modifier,
    showBackButton: Boolean,
    showContinueButton: Boolean,
    onContinueClick: () -> Unit,
    onBackClick: () -> Unit,
    currentPage: Int,
    totalPages: Int,
) {
    Row(
        modifier = modifier
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            val backButtonAlpha by animateFloatAsState(
                targetValue = if (showBackButton) 1f else 0f
            )
            TertiaryButton(
                text = stringResource(Strings.back),
                onClick = onBackClick,
                enabled = showBackButton,
                icon = ButtonIcon.Leading(
                    painter = ImageVector.vectorResource(DesignSystemDrawables.ic_arrow_backward),
                ),
                modifier = Modifier
                    .graphicsLayer {
                        alpha = backButtonAlpha
                    }
                    .modifyIf(showBackButton.not()) {
                        noSemantics()
                        // Hides it from accessibility/talkback when not
                        // "really" shown, so screen readers don't announce a
                        // disabled ghost button.
                    },
            )
        }
        StepIndicator(
            stepCount = totalPages,
            currentStep = currentPage,
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            val continueButtonAlpha by animateFloatAsState(
                targetValue = if (showContinueButton) 1f else 0f
            )
            PrimaryButton(
                text = stringResource(Strings.continue_btn),
                onClick = onContinueClick,
                enabled = showContinueButton,
                icon = ButtonIcon.Trailing(
                    painter = ImageVector.vectorResource(DesignSystemDrawables.ic_arrow_forward)
                ),
                modifier = Modifier
                    .graphicsLayer {
                        alpha = continueButtonAlpha
                    }
                    .modifyIf(showBackButton.not()) {
                        noSemantics()
                        // Hides it from accessibility/talkback when not
                        // "really" shown, so screen readers don't announce a
                        // disabled ghost button.
                    },
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun OnboardingScreenPreview() {
    GoPeakTheme {
        OnboardingScreen(
            callbacks = object : OnboardingUiCallbacks {
                override fun onFinish() = Unit
                override fun onSkipClick() = Unit
            }
        )
    }
}
