package com.example.pokedex.android.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokedex.android.ui.MyApplicationTheme
import com.example.pokedex.android.ui.screens.PokedexScreen
import com.example.pokedex.android.ui.viewmodels.PokedexViewModel
import com.example.pokedex.data.PokedexRepository
import com.example.pokedex.data.network.PokedexService

class MainActivity : ComponentActivity() {
    private val pokedexService = PokedexService()
    private val pokedexRepository = PokedexRepository(pokedexService)
    private val pokedexViewModel = PokedexViewModel(pokedexRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                PokedexScreen(pokedexViewModel = pokedexViewModel)
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
