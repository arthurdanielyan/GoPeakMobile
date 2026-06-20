package com.danielyan.gopeak.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.CoroutineScope

interface AppComponentContext : ComponentContext {

    val appRouter: AppRouter

    val componentScope: CoroutineScope

    fun <T : Any> Value<T>.observeWithLifecycle(observer: (T) -> Unit)
}