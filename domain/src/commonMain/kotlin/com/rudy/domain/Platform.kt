package com.rudy.domain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform