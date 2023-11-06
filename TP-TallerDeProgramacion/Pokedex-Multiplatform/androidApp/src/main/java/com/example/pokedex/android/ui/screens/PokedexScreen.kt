package com.example.pokedex.android.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import com.example.pokedex.android.ui.activities.PokedexScreenState
import com.example.pokedex.android.ui.screens.components.PokemonCard
import com.example.pokedex.android.ui.viewmodels.PokedexViewModel

@Composable
fun PokedexScreen(pokedexViewModel: PokedexViewModel) {
    val pokedexResponse by pokedexViewModel.pokedex.observeAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {
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

            when (pokedexResponse) {
                is PokedexScreenState.Loading -> {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = Modifier.size(40.dp))
                        Box(
                            modifier = Modifier
                                .size(300.dp, 500.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .background(MaterialTheme.colors.background),
                            contentAlignment = Alignment.Center,
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    text = "Loading...",
                                    fontSize = MaterialTheme.typography.h5.fontSize,
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(70.dp),
                                )
                            }
                        }
                    }
                }

                is PokedexScreenState.ShowPokedex -> {
                    val pokemonList =
                        (pokedexResponse as PokedexScreenState.ShowPokedex).pokemonList
                    Column {
                        Spacer(modifier = Modifier.size(50.dp))
                        LazyColumn(
                            modifier = Modifier
                                // .padding(horizontal = 40.dp, vertical = 100.dp)
                                .size(300.dp, 500.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .background(MaterialTheme.colors.surface),
                        ) {
                            items(pokemonList) { pokemon ->
                                PokemonCard(pokemon.name, pokemon.url)
                                Spacer(modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }

                is PokedexScreenState.Error -> {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = Modifier.size(40.dp))
                        Box(
                            modifier = Modifier
                                .size(300.dp, 500.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .background(MaterialTheme.colors.background),
                            contentAlignment = Alignment.Center,
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    text = "ERROR",
                                    color = Color.Red,
                                    fontSize = MaterialTheme.typography.h4.fontSize,
                                )
                                Text(
                                    text = "No internet connection",
                                    color = Color.Red,
                                    fontSize = MaterialTheme.typography.h6.fontSize,
                                )
                            }
                        }
                    }
                }

                null -> TODO()
            }
        }
    }
}
