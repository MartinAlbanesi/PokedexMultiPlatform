package com.example.pokedex.data

import com.example.pokedex.DatabaseDriverFactory
import com.example.pokedex.PokedexDB
import com.example.pokedex.domain.models.Pokemon

class PokedexDBRepository(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = PokedexDB(databaseDriverFactory.createDriver())
    private val dbQuery = database.pokedexQueries

    fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.deleteAllPokemon()
        }
    }

    fun getAllPokemon(): List<Pokemon> {
        return dbQuery.selectAllPokemon(::mapPokemonSelecting).executeAsList()
    }

    private fun mapPokemonSelecting(
        mapName: String,
        mapUrl: String,
    ): Pokemon {
        return Pokemon(name = mapName, url = mapUrl)
    }

    fun addPokemon(pokemon: Pokemon) {
        dbQuery.insertPokemon(
            name = pokemon.name,
            url = pokemon.url,
        )
    }
}
