package com.rudy.features

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform