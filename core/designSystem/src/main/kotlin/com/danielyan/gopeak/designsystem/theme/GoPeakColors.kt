package com.danielyan.gopeak.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * GoPeak's bespoke color roles. Deliberately NOT a Material 3 [androidx.compose.material3.ColorScheme]
 * — the app's palette uses custom roles (`brand`, `primary`, `accent`) that don't map cleanly to
 * Material semantics. Access via [GoPeakTheme.colors].
 */
@Immutable
data class GoPeakColors(
    /** Expressive orange — the highest-emphasis brand CTA (LargePrimaryButton). */
    val brand: Color,
    val onBrand: Color,
    /** Peach — the primary action color (PrimaryButton, SecondaryButton label). */
    val primary: Color,
    val onPrimary: Color,
    /** Blue highlight used for accents/metrics. Used as foreground and, at low alpha, as a fill. */
    val accent: Color,
    val onAccent: Color,
    /** [accent] at reduced opacity — background behind [accent]-colored content. */
    val accentContainer: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
    val isDark: Boolean,
)

val GoPeakDarkColors = GoPeakColors(
    brand = Color(0xFFFF6B00),
    onBrand = Color(0xFF572000),
    primary = Color(0xFFFFB693),
    onPrimary = Color(0xFF561F00),
    accent = Color(0xFF9CCAFF),
    onAccent = Color(0xFF0A2E4D),
    accentContainer = Color(0xFF9CCAFF).copy(alpha = 0.2f),
    background = Color(0xFF1D100A),
    onBackground = Color(0xFFF8DDD2),
    surface = Color(0xFF261812),
    onSurface = Color(0xFFF8DDD2),
    surfaceVariant = Color(0xFF41312A),
    onSurfaceVariant = Color(0xFFE2BFB0),
    outline = Color(0xFF5A4136),
    isDark = true,
)

// Light neutrals are derived (the Figma light frame was not reachable); `primary` is exact.
val GoPeakLightColors = GoPeakColors(
    brand = Color(0xFFFFAC8A),
    onBrand = Color(0xFF351000),
    primary = Color(0xFFA04100),
    onPrimary = Color(0xFFFFFFFF),
    accent = Color(0xFF00639A),
    onAccent = Color(0xFF0A2E4D),
    accentContainer = Color(0xFFCEE5FF).copy(alpha = 0.5f),
    background = Color(0xFFFFF8F5),
    onBackground = Color(0xFF231A14),
    surface = Color(0xFFFFF8F5),
    onSurface = Color(0xFF1D100A),
    surfaceVariant = Color(0xFFF0E4DD),
    onSurfaceVariant = Color(0xFF5A4136),
    outline = Color(0xFFB8A196),
    isDark = false,
)
