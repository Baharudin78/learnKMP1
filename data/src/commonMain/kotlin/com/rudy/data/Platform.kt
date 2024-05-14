package com.rudy.data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform