package com.danielyan.gopeak.decompose

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Bridges a coroutines [Flow] to a Decompose [Value], so components can keep their state
 * logic in Flow while exposing a [Value] at the API boundary. A [Value] is observable from
 * both Compose (`subscribeAsState`) and SwiftUI (`ObservableValue`), whereas a `StateFlow`
 * needs a custom bridge on iOS.
 *
 * Collection runs on [scope]; when it is cancelled (component destroyed) the collector stops,
 * so there is nothing extra to dispose.
 */
fun <T : Any> Flow<T>.asValue(
    initialValue: T,
    scope: CoroutineScope,
): Value<T> {
    val value = MutableValue(initialValue)
    scope.launch {
        collect { value.value = it }
    }
    return value
}
