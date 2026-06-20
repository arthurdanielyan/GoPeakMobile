package com.danielyan.gopeak.decompose

import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.children.NavigationSource
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.Lifecycle
import kotlinx.serialization.KSerializer

@Suppress("UNCHECKED_CAST")
inline fun <CONTEXT : AppComponentContext, reified CONFIG : ScreenConfig, COMPONENT : Any> CONTEXT.appChildStack(
    source: NavigationSource<StackNavigation.Event<CONFIG>>,
    serializer: KSerializer<CONFIG>?,
    initialConfiguration: CONFIG,
    key: String = "DefaultChildStack",
    handleBackButton: Boolean = true,
    noinline childFactory: (configuration: CONFIG, AppComponentContext) -> COMPONENT,
    noinline onNavigate: ((configuration: CONFIG, onComplete: (Boolean) -> Unit) -> Unit)? = null,
    noinline onPop: (((Boolean) -> Unit) -> Unit)? = null,
): Value<ChildStack<CONFIG, COMPONENT>> {
    return childStack(
        source = source,
        serializer = serializer,
        initialConfiguration = initialConfiguration,
        key = key,
        handleBackButton = handleBackButton,
        childFactory = { config, childContext ->
            val defaultComponentContext = DefaultAppComponentContext(childContext)
            val appComponentContext =
                object : AppComponentContext by defaultComponentContext {
                    override val appRouter = AppRouterImpl(
                        onNavigate = { config, onComplete ->
                            (config as? CONFIG)?.let { config ->
                                onNavigate?.invoke(config, onComplete)
                            } ?: onComplete(false)
                        },
                        onPop = onPop,
                        parentRouter = this@appChildStack.appRouter
                    )
                }

            childFactory(config, appComponentContext)
        }
    )
}

fun <CONTEXT : AppComponentContext, CONFIG : ScreenConfig, COMPONENT : Any> CONTEXT.appChildSlot(
    source: NavigationSource<SlotNavigation.Event<CONFIG>>,
    serializer: KSerializer<CONFIG>?,
    initialConfiguration: CONFIG? = null,
    key: String = "DefaultChildSlot",
    handleBackButton: Boolean = true,
    childFactory: (configuration: CONFIG, AppComponentContext) -> COMPONENT,
): Value<ChildSlot<CONFIG, COMPONENT>> {
    return childSlot(
        source = source,
        serializer = serializer,
        initialConfiguration = { initialConfiguration },
        key = key,
        handleBackButton = handleBackButton,
        childFactory = { config, childContext ->
            val defaultComponentContext = DefaultAppComponentContext(childContext)
            val appComponentContext =
                object : AppComponentContext by defaultComponentContext {
                    override val appRouter = this@appChildSlot.appRouter
                }

            childFactory(config, appComponentContext)
        }
    )
}

fun <CONTEXT : AppComponentContext> CONTEXT.appChildContext(
    key: String,
    lifecycle: Lifecycle? = null
): AppComponentContext =
    object : AppComponentContext by DefaultAppComponentContext(
        childContext(
            key = key,
            lifecycle = lifecycle
        )
    ) {
        override val appRouter: AppRouter = this@appChildContext.appRouter
    }
