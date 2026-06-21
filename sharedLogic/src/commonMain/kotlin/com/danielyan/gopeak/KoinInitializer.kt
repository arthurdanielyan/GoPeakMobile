package com.danielyan.gopeak

import com.danielyan.gopeak.root.impl.di.rootModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            rootModule
        )
    }
}

/**
 * No-argument entry point for iOS/Swift so it doesn't have to deal with the
 * optional [KoinAppDeclaration] closure. Mirrors [GoPeakApplication] on Android.
 */
fun doInitKoin() = initKoin()