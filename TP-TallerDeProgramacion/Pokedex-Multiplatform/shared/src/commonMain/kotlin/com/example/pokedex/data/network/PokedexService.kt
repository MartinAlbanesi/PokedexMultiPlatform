package com.example.pokedex.data.network

import com.example.pokedex.data.network.models.Pokedex
import com.example.pokedex.data.network.models.PokedexResults
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
        return try {
            val response = client.get("https://pokeapi.co/api/v2/pokemon/?limit=800")
            response.body<Pokedex>().results
        } catch (e: Exception) {
            emptyList()
        }
    }
}
