package com.example.pokedex.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class PokedexService {

    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                },
            )
        }
    }

    suspend fun getPokedex(): List<PokedexResults> {
        val response = client.get("https://pokeapi.co/api/v2/pokemon/?limit=800")
        return response.body<Pokedex>().results
    }
}
