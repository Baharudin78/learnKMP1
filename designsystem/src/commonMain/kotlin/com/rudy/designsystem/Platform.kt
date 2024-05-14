package com.rudy.designsystem

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform