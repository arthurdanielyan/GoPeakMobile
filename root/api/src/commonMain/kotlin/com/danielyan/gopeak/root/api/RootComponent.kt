package com.danielyan.gopeak.root.api

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.danielyan.gopeak.decompose.AppComponentContext
import kotlinx.coroutines.flow.StateFlow

interface RootComponent {

    val uiState: StateFlow<RootViewState>
    val childStack: Value<ChildStack<RootScreenConfig, Any>>

    fun onBack()

    fun interface Factory {
        operator fun invoke(
            appComponentContext: AppComponentContext
        ): RootComponent
    }
}