package com.danielyan.gopeak.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.danielyan.gopeak.designsystem.theme.GoPeakTheme

/**
 * A button icon that can sit on exactly one side — the type makes "leading AND trailing at once"
 * unrepresentable. Pass [None] for no icon.
 */
@Immutable
sealed interface ButtonIcon {

    data object None : ButtonIcon

    data class Leading(
        val painter: ImageVector,
        val contentDescription: String? = null
    ) : ButtonIcon

    data class Trailing(
        val painter: ImageVector,
        val contentDescription: String? = null
    ) : ButtonIcon
}

private val ButtonIcon.leading: ImageVector?
    get() = (this as? ButtonIcon.Leading)?.painter

private val ButtonIcon.trailing: ImageVector?
    get() = (this as? ButtonIcon.Trailing)?.painter

private val ButtonIcon.contentDescription: String?
    get() = when (this) {
        is ButtonIcon.Leading -> contentDescription
        is ButtonIcon.Trailing -> contentDescription
        ButtonIcon.None -> null
    }


/** High-emphasis brand CTA (filled orange). Optional single icon. */
@Composable
fun LargePrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ButtonIcon = ButtonIcon.None,
    enabled: Boolean = true,
) {
    TextIconButton(
        text = text,
        onClick = onClick,
        textStyle = GoPeakTheme.typography.buttonLarge,
        contentColor = GoPeakTheme.colors.onBrand,
        backgroundColor = GoPeakTheme.colors.brand,
        shape = GoPeakTheme.shapes.small,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
        leadingIcon = icon.leading,
        trailingIcon = icon.trailing,
        iconContentDescription = icon.contentDescription,
        enabled = enabled,
        modifier = modifier,
    )
}

/** High-emphasis alternative (outlined). Optional single icon. */
@Composable
fun LargeSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ButtonIcon = ButtonIcon.None,
    enabled: Boolean = true,
) {
    TextIconButton(
        text = text,
        onClick = onClick,
        textStyle = GoPeakTheme.typography.buttonLarge,
        contentColor = GoPeakTheme.colors.onBackground,
        border = BorderStroke(2.dp, GoPeakTheme.colors.outline),
        shape = GoPeakTheme.shapes.small,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp),
        leadingIcon = icon.leading,
        trailingIcon = icon.trailing,
        iconContentDescription = icon.contentDescription,
        enabled = enabled,
        modifier = modifier,
    )
}

/** Compact primary action (filled peach). Supports a leading OR trailing icon. */
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ButtonIcon = ButtonIcon.None,
    enabled: Boolean = true,
) {
    TextIconButton(
        text = text,
        onClick = onClick,
        textStyle = GoPeakTheme.typography.buttonCompact,
        contentColor = GoPeakTheme.colors.onPrimary,
        backgroundColor = GoPeakTheme.colors.primary,
        shape = GoPeakTheme.shapes.small,
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 8.dp),
        leadingIcon = icon.leading,
        trailingIcon = icon.trailing,
        iconContentDescription = icon.contentDescription,
        enabled = enabled,
        modifier = modifier,
    )
}

/** Compact low-emphasis action (text only, primary-colored). Supports a leading OR trailing icon. */
@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ButtonIcon = ButtonIcon.None,
    enabled: Boolean = true,
) {
    TextIconButton(
        text = text,
        onClick = onClick,
        textStyle = GoPeakTheme.typography.buttonCompact,
        contentColor = GoPeakTheme.colors.primary,
        shape = GoPeakTheme.shapes.small,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        leadingIcon = icon.leading,
        trailingIcon = icon.trailing,
        iconContentDescription = icon.contentDescription,
        enabled = enabled,
        modifier = modifier,
    )
}

/** Lowest-emphasis action (text only, muted). No icon. */
@Composable
fun TertiaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ButtonIcon = ButtonIcon.None,
    enabled: Boolean = true,
) {
    TextIconButton(
        text = text,
        onClick = onClick,
        textStyle = GoPeakTheme.typography.buttonText,
        contentColor = GoPeakTheme.colors.onSurfaceVariant,
        shape = GoPeakTheme.shapes.small,
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        leadingIcon = icon.leading,
        trailingIcon = icon.trailing,
        enabled = enabled,
        modifier = modifier,
    )
}

/**
 * Internal button impl supporting both leading and trailing icons. Public buttons expose a single
 * [ButtonIcon] so only one side is ever set.
 */
@Composable
private fun TextIconButton(
    text: String,
    onClick: () -> Unit,
    textStyle: TextStyle,
    contentColor: Color,
    shape: Shape,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Unspecified,
    border: BorderStroke? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    iconContentDescription: String? = null,
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .clip(shape)
            .then(if (backgroundColor.isSpecified) Modifier.background(backgroundColor) else Modifier)
            .then(if (border != null) Modifier.border(border, shape) else Modifier)
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(),
                enabled = enabled,
                onClick = onClick,
            )
            .padding(contentPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingIcon?.let {
            Icon(
                imageVector = it,
                contentDescription = iconContentDescription,
                tint = contentColor,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(8.dp))
        }
        Text(
            text = text,
            style = textStyle,
            color = contentColor,
        )
        trailingIcon?.let {
            Spacer(Modifier.width(8.dp))
            Icon(
                imageVector = it,
                contentDescription = iconContentDescription,
                tint = contentColor,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun ButtonsPreview() {
    GoPeakTheme {
        Column(
            modifier = Modifier
                .background(GoPeakTheme.colors.background)
                .width(250.dp)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            LargePrimaryButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Get Started",
                onClick = {}
            )
            LargeSecondaryButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Watch Demo",
                onClick = {}
            )
            PrimaryButton(
                text = "Continue",
                onClick = {}
            )
            SecondaryButton(
                text = "Login",
                onClick = {}
            )
            TertiaryButton(
                text = "Skip",
                onClick = {}
            )
        }
    }
}
