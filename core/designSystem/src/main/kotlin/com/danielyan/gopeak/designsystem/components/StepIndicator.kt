package com.danielyan.gopeak.designsystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.danielyan.gopeak.designsystem.theme.GoPeakTheme

@Composable
fun StepIndicator(
    modifier: Modifier = Modifier,
    stepCount: Int,
    currentStep: Int,
    dotSize: Dp = 8.dp,
    spacing: Dp = 8.dp
) {
    val width = remember(dotSize, stepCount) {
        spacing * (stepCount - 1) + dotSize * (stepCount - 1) + dotSize * ActiveScale
    }
    Row(
        modifier = modifier
            .width(width),
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        repeat(stepCount) {
            Step(
                thisIndex = it,
                activeIndex = currentStep,
                dotSize = dotSize,
            )
        }
    }
}

@Composable
private fun Step(
    thisIndex: Int,
    activeIndex: Int,
    dotSize: Dp = 8.dp,
) {
    val isActive = thisIndex == activeIndex
    val widthScale by animateFloatAsState(
        targetValue = if (isActive) {
            ActiveScale
        } else 1f,
        animationSpec = MaterialTheme.motionScheme.fastSpatialSpec()
    )

    val density = LocalDensity.current
    val offset by animateFloatAsState(
        if(activeIndex < thisIndex) {
            with(density) { dotSize.toPx() * ActiveScale - dotSize.toPx() }
        } else {
            0f
        }
    )

    val color by animateColorAsState(
        targetValue = if (isActive) {
            GoPeakTheme.colors.primary
        } else {
            GoPeakTheme.colors.surfaceVariant
        }
    )

    Canvas(
        modifier = Modifier
            .size(dotSize)
            .graphicsLayer {
                translationX = offset
            }
    ) {
        drawRoundRect(
            color = color,
            cornerRadius = CornerRadius(size.height * 0.5f, size.height * 0.5f),
            size = Size(
                height = size.height,
                width = size.height * widthScale,
            ),
        )
    }
}

@PreviewLightDark
@Composable
private fun StepIndicatorPreview() {
    GoPeakTheme {
        Box(
            modifier = Modifier
                .background(GoPeakTheme.colors.background)
                .padding(16.dp)
        ) {
            StepIndicator(
                stepCount = 4,
                currentStep = 0
            )
        }
    }
}

private const val ActiveScale = 3f
