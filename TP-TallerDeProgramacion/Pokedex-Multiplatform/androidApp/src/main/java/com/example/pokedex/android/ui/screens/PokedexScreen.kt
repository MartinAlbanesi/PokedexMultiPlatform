package com.example.pokedex.android.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(start = 15.dp, end = 45.dp, top = 85.dp, bottom = 75.dp),
            ) {
                Spacer(modifier = Modifier.size(70.dp))
                ShowPokedexScreen((pokedexResponse as PokedexScreenState))
            }
        }
    }
}

@Composable
fun ShowPokedexScreen(pokedexResponse: PokedexScreenState) {
    val backgroundColor =
        if (pokedexResponse is PokedexScreenState.Error || pokedexResponse is PokedexScreenState.Loading) {
            MaterialTheme.colors.background
        } else {
            MaterialTheme.colors.surface
        }

    LazyColumn(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .border(10.dp, Color.White, shape = RoundedCornerShape(15.dp))
            .background(backgroundColor)
            .fillMaxSize(),
    ) {
        when (pokedexResponse) {
            is PokedexScreenState.ShowPokedex -> {
                val pokemonList = (pokedexResponse).pokemonList
                item {
                    Spacer(modifier = Modifier.size(15.dp))
                }
                items(pokemonList) { pokemon ->
                    Spacer(modifier = Modifier.size(5.dp))
                    PokemonCard(pokemon.name, pokemon.url)
                    Spacer(modifier = Modifier.size(5.dp))
                }
                item {
                    Spacer(modifier = Modifier.size(15.dp))
                }
            }

            is PokedexScreenState.Loading -> {
                item {
                    Spacer(modifier = Modifier.size(200.dp))
                }
                item {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize(),
                    ) {
                        val colorLoading = listOf(
                            Color(0xFF16FFE4),
                            Color(0xFFA3FCF4),
                        )
                        Text(
                            text = "Loading...",
                            modifier = Modifier.offset(2.dp, 3.dp),
                            style = TextStyle(
                                brush = Brush.horizontalGradient(
                                    colors = colorLoading,
                                    tileMode = TileMode.Mirror,
                                ),
                            ),
                            fontSize = 50.sp,
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(70.dp),
                            color = Color(0xFF16E4FF),
                        )
                    }
                }
            }

            is PokedexScreenState.Error -> {
                item {
                    Spacer(modifier = Modifier.size(170.dp))
                }
                item {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize(),
                    ) {
                        val colorError = listOf(
                            Color(0xFFF14324),
                            Color(0xFFFD95A4),
                        )
                        Text(
                            text = "Oops!",
                            modifier = Modifier.offset(2.dp, 3.dp),
                            style = TextStyle(
                                brush = Brush.horizontalGradient(
                                    colors = colorError,
                                    tileMode = TileMode.Mirror,
                                ),
                            ),
                            fontSize = 64.sp,
                        )

                        Spacer(modifier = Modifier.size(5.dp))
                        Text(
                            text = "No internet connection",
                            color = Color.White,
                            fontSize = MaterialTheme.typography.h6.fontSize,
                        )
                    }
                }
            }
        }
    }
}
