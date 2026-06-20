package com.danielyan.gopeak

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform