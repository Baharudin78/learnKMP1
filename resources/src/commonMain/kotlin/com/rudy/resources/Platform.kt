package com.rudy.resources

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform