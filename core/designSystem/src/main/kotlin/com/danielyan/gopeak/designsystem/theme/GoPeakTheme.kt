package com.danielyan.gopeak.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalGoPeakColors = staticCompositionLocalOf<GoPeakColors> {
    error("GoPeakColors not provided. Wrap your content in GoPeakTheme { }.")
}

val LocalGoPeakTypography = staticCompositionLocalOf { GoPeakDefaultTypography }

val LocalGoPeakShapes = staticCompositionLocalOf { GoPeakDefaultShapes }

/**
 * Entry point for GoPeak design tokens — the bespoke counterpart of `MaterialTheme`.
 * Read tokens with `GoPeakTheme.colors`, `GoPeakTheme.typography`, `GoPeakTheme.shapes`.
 */
object GoPeakTheme {
    val colors: GoPeakColors
        @Composable @ReadOnlyComposable get() = LocalGoPeakColors.current

    val typography: GoPeakTypography
        @Composable @ReadOnlyComposable get() = LocalGoPeakTypography.current

    val shapes: GoPeakShapes
        @Composable @ReadOnlyComposable get() = LocalGoPeakShapes.current
}

@Composable
fun GoPeakTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) GoPeakDarkColors else GoPeakLightColors
    CompositionLocalProvider(
        LocalGoPeakColors provides colors,
        LocalGoPeakTypography provides GoPeakDefaultTypography,
        LocalGoPeakShapes provides GoPeakDefaultShapes,
        content = content,
    )
}
