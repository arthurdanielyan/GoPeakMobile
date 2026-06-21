package com.danielyan.gopeak

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.destroy
import com.arkivanov.essenty.lifecycle.resume
import com.danielyan.gopeak.decompose.DefaultAppComponentContext
import com.danielyan.gopeak.root.api.RootComponent
import org.koin.mp.KoinPlatform

/**
 * iOS counterpart of the root-component creation done in `MainActivity` on Android.
 *
 * Android uses Decompose's `retainedComponent { ... }` which is driven by the Activity
 * lifecycle. On iOS there is no such host, so we own a [LifecycleRegistry] manually and
 * resume it here / destroy it from Swift (`RootObservableModel.deinit`).
 *
 * The component itself is built exactly like on Android: the Koin-provided
 * [RootComponent.Factory] is invoked with a [DefaultAppComponentContext].
 */
class RootComponentHolder {

    private val lifecycle = LifecycleRegistry()

    val component: RootComponent

    init {
        val componentContext = DefaultComponentContext(lifecycle = lifecycle)
        val factory = KoinPlatform.getKoin().get<RootComponent.Factory>()
        component = factory(DefaultAppComponentContext(componentContext))
        lifecycle.resume()
    }

    fun destroy() {
        lifecycle.destroy()
    }
}
