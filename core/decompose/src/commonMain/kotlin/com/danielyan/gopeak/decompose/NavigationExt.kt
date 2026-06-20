package com.danielyan.gopeak.decompose

import com.arkivanov.decompose.router.stack.StackNavigator

inline fun <C : Any> StackNavigator<C>.replaceWith(configuration: C, crossinline onComplete: () -> Unit = { }) {
    navigate(transformer = { stack ->
        if(stack.isEmpty() || stack.last()::class != configuration::class) {
            listOf(configuration)
        } else {
            stack
        }
    }, onComplete = { _, _ -> onComplete() })
}
