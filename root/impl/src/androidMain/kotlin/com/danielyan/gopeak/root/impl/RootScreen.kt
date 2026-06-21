package com.danielyan.gopeak.root.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.danielyan.gopeak.root.api.RootComponent

@Composable
fun RootScreen(
    component: RootComponent
) {
    Children(
        modifier = Modifier.fillMaxSize(),
        stack = component.childStack,
        animation = stackAnimation(slide())
    ) {
        when (val childComponent = it.instance) {
            else ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .systemBarsPadding(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Not Yet Implemented",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
        }
    }
}