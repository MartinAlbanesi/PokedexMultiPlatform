package com.example.pokedex.data

import com.example.pokedex.data.network.PokedexService
import com.example.pokedex.domain.models.Pokemon

interface IPokedexRepository {

    suspend fun getPokedex(): List<Pokemon>
}

class PokedexRepository(
    private val pokedexService: PokedexService,
) : IPokedexRepository {
    override suspend fun getPokedex(): List<Pokemon> {
        if (pokedexService.getPokedex().isEmpty()) {
            return emptyList()
        }
        return pokedexService.getPokedex().map {
            Pokemon(it.name, it.url)
        }
    }
}
