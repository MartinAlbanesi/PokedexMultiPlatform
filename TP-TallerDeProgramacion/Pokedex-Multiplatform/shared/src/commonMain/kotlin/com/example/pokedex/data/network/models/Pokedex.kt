package com.example.pokedex.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokedex(
    @SerialName("count")
    val count: Int,
    @SerialName("next")
    val next: String,
    @SerialName("results")
    val results: List<PokedexResults>,
)
