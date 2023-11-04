package com.example.pokedex

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform