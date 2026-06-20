package com.danielyan.gopeak.decompose

interface AppRouter {

    fun navigate(dest: ScreenConfig)

    fun pop()
}