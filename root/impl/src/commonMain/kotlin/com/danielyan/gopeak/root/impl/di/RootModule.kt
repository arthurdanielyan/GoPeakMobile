package com.danielyan.gopeak.root.impl.di

import com.danielyan.gopeak.root.api.RootComponent
import com.danielyan.gopeak.root.impl.RootComponentFactory
import org.koin.dsl.module

val rootModule = module {

    factory<RootComponent.Factory> {
        RootComponentFactory()
    }
}