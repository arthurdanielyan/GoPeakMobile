package com.danielyan.gopeak.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

/**
 * GoPeak type scale (from Figma). Uses the device's default font family on Android — no bundled
 * typeface. Access via [GoPeakTheme.typography].
 */
@Immutable
data class GoPeakTypography(
    val logo: TextStyle,
    val heading: TextStyle,
    val eyebrow: TextStyle,
    val body: TextStyle,
    val label: TextStyle,
    val buttonLarge: TextStyle,
    val buttonCompact: TextStyle,
    val buttonText: TextStyle,
)

fun goPeakTypography(fontFamily: FontFamily = FontFamily.Default) = GoPeakTypography(
    logo = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.05).em,
    ),
    heading = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
    ),
    eyebrow = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 3.2.sp,
    ),
    body = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    label = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.55.sp,
    ),
    buttonLarge = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
    ),
    buttonCompact = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.55.sp,
    ),
    buttonText = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
)

val GoPeakDefaultTypography = goPeakTypography()
