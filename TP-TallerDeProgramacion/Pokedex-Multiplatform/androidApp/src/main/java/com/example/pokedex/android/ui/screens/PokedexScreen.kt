package com.example.pokedex.android.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pokedex.android.R
import com.example.pokedex.android.ui.screens.components.PokemonCard
import com.example.pokedex.android.ui.viewmodels.PokedexViewModel

@Composable
fun PokedexScreen(pokedexViewModel: PokedexViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {
        val pokedex by pokedexViewModel.pokedex.observeAsState(emptyList())

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokedex_background),
                contentDescription = "Pokedex",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize(),
            )
            Column {
                Spacer(modifier = Modifier.size(50.dp))
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 40.dp, vertical = 100.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.Cyan),
                ) {
                    items(pokedex) { pokemon ->
                        PokemonCard(name = pokemon.name, url = pokemon.url)
                    }
                }
            }
        }
    }
}
