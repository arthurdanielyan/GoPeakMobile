package com.danielyan.gopeak.designsystem.ext

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun SpacerHeight(height: Dp) {
    Spacer(Modifier.height(height))
}

@Composable
fun SpacerWidth(width: Dp) {
    Spacer(Modifier.width(width))
}

@Composable
fun RowScope.SpacerWeight() {
    Spacer(Modifier.weight(1f))
}

@Composable
fun ColumnScope.SpacerWeight() {
    Spacer(Modifier.weight(1f))
}
