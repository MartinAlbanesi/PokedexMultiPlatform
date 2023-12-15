package com.example.pokedex.android.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedex.android.R
import com.example.pokedex.utils.ImageBuilder

@Composable
fun PokemonCard(name: String, url: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 18.dp)
            .clip(CircleShape)
            .background(Color(0xFF1EB7F5)),
    ) {
        AsyncImage(
            model = ImageBuilder.buildPokemonImageByUrl(url),
            contentDescription = "Pokémon image",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.1f))
                .padding(4.dp),
            placeholder = painterResource(R.drawable.pokeball),
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            name.replaceFirstChar { it.uppercase() }.replace("-", " "),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFFC6FFFF),
        )
        Spacer(modifier = Modifier.fillMaxWidth())
        AsyncImage(
            model = ImageBuilder.buildPokemonImageByUrl(url),
            contentDescription = "Pokémon image",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Gray.copy(alpha = 0.1f))
                .padding(3.dp),
            placeholder = painterResource(R.drawable.pokeball),
        )
    }
}
