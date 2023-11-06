package com.example.pokedex.android.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokedex.DatabaseDriverFactory
import com.example.pokedex.android.ui.MyApplicationTheme
import com.example.pokedex.android.ui.screens.PokedexScreen
import com.example.pokedex.android.ui.viewmodels.PokedexViewModel
import com.example.pokedex.data.PokedexDBRepository
import com.example.pokedex.data.PokedexRepository
import com.example.pokedex.data.network.PokedexService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pokedexService = PokedexService()
        val pokedexRepository = PokedexRepository(pokedexService)
        val pokedexDBRepository = PokedexDBRepository(DatabaseDriverFactory(this))
        val pokedexViewModel = PokedexViewModel(pokedexRepository, pokedexDBRepository, this)
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
