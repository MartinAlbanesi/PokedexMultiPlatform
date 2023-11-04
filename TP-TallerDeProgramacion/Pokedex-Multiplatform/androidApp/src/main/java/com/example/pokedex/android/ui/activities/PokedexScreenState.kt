package com.example.pokedex.android.ui.activities

import com.example.pokedex.data.Pokedex

sealed class PokedexScreenState {
    object Loading : PokedexScreenState()

    object Error : PokedexScreenState()

    class ShowPokedex(val pokedex: Pokedex) : PokedexScreenState()
}
