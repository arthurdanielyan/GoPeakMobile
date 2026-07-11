package com.danielyan.gopeak.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/** GoPeak corner-radius scale (from Figma: 4 / 8 / 12 / 16). Access via [GoPeakTheme.shapes]. */
@Immutable
data class GoPeakShapes(
    val extraSmall: Shape = RoundedCornerShape(4.dp),
    val small: Shape = RoundedCornerShape(8.dp),
    val medium: Shape = RoundedCornerShape(12.dp),
    val large: Shape = RoundedCornerShape(16.dp),
)

val GoPeakDefaultShapes = GoPeakShapes()
