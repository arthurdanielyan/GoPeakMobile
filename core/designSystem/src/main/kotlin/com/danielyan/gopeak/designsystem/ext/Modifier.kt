package com.danielyan.gopeak.designsystem.ext

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.clearAndSetSemantics

fun Modifier.clickableWithoutIndication(
    onClick: () -> Unit
) = composed {
    Modifier
        .clickable(
            onClick = onClick,
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        )
}

inline fun Modifier.modifyIf(
    condition: Boolean,
    modify: Modifier.() -> Modifier
): Modifier =
    then(
        if(condition) {
            Modifier.modify()
        } else {
            Modifier
        }
    )

fun Modifier.noSemantics() =
    this.clearAndSetSemantics {}