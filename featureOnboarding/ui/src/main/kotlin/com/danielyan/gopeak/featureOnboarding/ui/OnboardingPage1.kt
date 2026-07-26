package com.danielyan.gopeak.featureOnboarding.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.danielyan.gopeak.designsystem.components.BrandPrimaryButton
import com.danielyan.gopeak.designsystem.components.BrandSecondaryButton
import com.danielyan.gopeak.designsystem.components.ButtonIcon
import com.danielyan.gopeak.designsystem.ext.SpacerHeight
import com.danielyan.gopeak.designsystem.ext.SpacerWeight
import com.danielyan.gopeak.designsystem.theme.GoPeakTheme
import com.danielyan.gopeak.designsystem.R.drawable as DesignSystemDrawables
import com.danielyan.gopeak.featureOnboarding.ui.R.drawable as Drawables
import com.danielyan.gopeak.featureOnboarding.ui.R.string as Strings

@Composable
internal fun OnboardingPage1(
    modifier: Modifier = Modifier,
    onGetStartedClick: () -> Unit,
    onSkipClick: () -> Unit,
) {
    Box(
        modifier = modifier,
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .background(GoPeakTheme.colors.dim),
            painter = painterResource(Drawables.onboarding_hero),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        OnboardingPage1Content(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onGetStartedClick = onGetStartedClick,
            onSkipClick = onSkipClick,
        )
    }
}

@Composable
private fun OnboardingPage1Content(
    modifier: Modifier = Modifier,
    onGetStartedClick: () -> Unit,
    onSkipClick: () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        SpacerWeight()
        Text(
            text = stringResource(Strings.onboarding1_app_description),
            style = GoPeakTheme.typography.eyebrow,
            color = GoPeakTheme.colors.primary
        )
        SpacerHeight(4.dp)
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = GoPeakTheme.colors.onBackground)) {
                    append(stringResource(Strings.onboarding1_title_1))
                }
                append(" ")
                withStyle(style = SpanStyle(color = GoPeakTheme.colors.brandEmphasis)) {
                    append(stringResource(Strings.onboarding1_title_2))
                }
            },
            style = GoPeakTheme.typography.heading,
            color = GoPeakTheme.colors.primary
        )
        SpacerHeight(16.dp)
        Text(
            text = stringResource(Strings.onboarding1_description),
            style = GoPeakTheme.typography.body,
            color = GoPeakTheme.colors.primary
        )
        SpacerHeight(24.dp)
        BrandPrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(Strings.onboarding1_proceed_btn),
            onClick = onGetStartedClick,
            icon = ButtonIcon.Trailing(
                painter = ImageVector.vectorResource(DesignSystemDrawables.ic_arrow_forward)
            )
        )
        SpacerHeight(16.dp)
        BrandSecondaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(Strings.onboarding_skip),
            onClick = onSkipClick,
        )
        SpacerHeight(40.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FeatureCard(
                modifier = Modifier.weight(1f),
                color = GoPeakTheme.colors.onSurface,
                iconRes = Drawables.ic_ft_live_tracking,
                featureDescription = stringResource(Strings.ft_live_tracking),
            )
            FeatureCard(
                modifier = Modifier.weight(1f),
                color = GoPeakTheme.colors.accent,
                iconRes = Drawables.ic_ft_growth,
                featureDescription = stringResource(Strings.ft_growth),
            )
        }
        SpacerHeight(24.dp)
    }
}

@Composable
private fun FeatureCard(
    modifier: Modifier = Modifier,
    color: Color,
    iconRes: Int,
    featureDescription: String,
) {
    Box(
        modifier = modifier
            .clip(GoPeakTheme.shapes.small)
            .background(color)
            .height(IntrinsicSize.Min),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(x = 2.dp)
                .clip(GoPeakTheme.shapes.small)
                .background(GoPeakTheme.colors.surfaceVariant)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(iconRes),
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = color,
            )
            Text(
                text = featureDescription,
                color = GoPeakTheme.colors.onSurface,
                style = GoPeakTheme.typography.label,
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun OnboardingPage1Preview() {
    GoPeakTheme {
        OnboardingPage1(
            modifier = Modifier.fillMaxSize(),
            onGetStartedClick = {},
            onSkipClick = {},
        )
    }
}
