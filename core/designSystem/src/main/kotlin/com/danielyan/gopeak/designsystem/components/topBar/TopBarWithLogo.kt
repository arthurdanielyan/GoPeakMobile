package com.danielyan.gopeak.designsystem.components.topBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.danielyan.gopeak.designsystem.components.TertiaryButton
import com.danielyan.gopeak.designsystem.theme.GoPeakTheme
import com.danielyan.gopeak.designsystem.R.drawable as Drawables

@Composable
fun TopBarWithLogo(
    modifier: Modifier = Modifier,
    trailingContent: @Composable (RowScope.() -> Unit)? = null
) {
    BasicTopBar(
        modifier = modifier,
        titleContent = {
            Icon(
                modifier = Modifier.height(32.dp),
                imageVector = ImageVector.vectorResource(Drawables.brand_logo),
                contentDescription = null,
                tint = GoPeakTheme.colors.primary,
            )
        },
        trailingContent = trailingContent,
    )
}

@PreviewLightDark
@Composable
private fun TopBarWithLogoPreview() {
    GoPeakTheme {
        Column(
            modifier = Modifier
                .background(GoPeakTheme.colors.background)
        ) {
            TopBarWithLogo(
                modifier = Modifier.fillMaxWidth(),
                trailingContent = {
                    TertiaryButton(
                        text = "Skip",
                        onClick = {}
                    )
                }
            )
        }
    }
}
