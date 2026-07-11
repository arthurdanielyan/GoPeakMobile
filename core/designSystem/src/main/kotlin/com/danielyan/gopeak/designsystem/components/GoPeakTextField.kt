package com.danielyan.gopeak.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.danielyan.gopeak.designsystem.theme.GoPeakTheme

/**
 * GoPeak text field. Has both a [leadingIcon] (decorative) and a
 * [trailingIcon] slot — the trailing slot is a composable so it can host an interactive control
 * such as a password-reveal toggle.
 */
@Composable
fun GoPeakTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: Painter? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true,
    enabled: Boolean = true,
) {
    val colors = GoPeakTheme.colors
    Column(modifier) {
        if (label != null) {
            BasicTextLabel(label)
            Spacer(Modifier.height(4.dp))
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = singleLine,
            textStyle = GoPeakTheme.typography.body.merge(
                androidx.compose.ui.text.TextStyle(color = colors.onSurface),
            ),
            cursorBrush = SolidColor(colors.primary),
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .clip(GoPeakTheme.shapes.small)
                        .background(colors.surfaceVariant)
                        .border(1.dp, colors.outline, GoPeakTheme.shapes.small)
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (leadingIcon != null) {
                        Icon(
                            painter = leadingIcon,
                            contentDescription = null,
                            tint = colors.onSurfaceVariant,
                            modifier = Modifier.size(16.dp),
                        )
                        Spacer(Modifier.width(12.dp))
                    }
                    Box(Modifier.weight(1f)) {
                        if (value.isEmpty() && placeholder != null) {
                            androidx.compose.foundation.text.BasicText(
                                text = placeholder,
                                style = GoPeakTheme.typography.body.merge(
                                    androidx.compose.ui.text.TextStyle(
                                        color = colors.onSurfaceVariant.copy(alpha = 0.5f),
                                    ),
                                ),
                            )
                        }
                        innerTextField()
                    }
                    if (trailingIcon != null) {
                        Spacer(Modifier.width(12.dp))
                        trailingIcon()
                    }
                }
            },
        )
    }
}

@Composable
private fun BasicTextLabel(text: String) {
    androidx.compose.foundation.text.BasicText(
        text = text.uppercase(),
        style = GoPeakTheme.typography.label.merge(
            androidx.compose.ui.text.TextStyle(color = GoPeakTheme.colors.onSurfaceVariant),
        ),
    )
}

@PreviewLightDark
@Composable
private fun GoPeakTextFieldPreview() {
    GoPeakTheme {
        Box(
            Modifier
                .background(GoPeakTheme.colors.background)
                .padding(24.dp),
        ) {
            GoPeakTextField(
                value = "",
                onValueChange = {},
                label = "Full name",
                placeholder = "John Doe",
            )
        }
    }
}
