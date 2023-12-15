package com.example.pokedex.android.ui.activities

import com.example.pokedex.domain.models.Pokemon

sealed class PokedexScreenState {
    object Loading : PokedexScreenState()

    object Error : PokedexScreenState()

    class ShowPokedex(val pokemonList: List<Pokemon>) : PokedexScreenState()
}
