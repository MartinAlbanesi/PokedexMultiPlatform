package com.example.pokedex.android.ui.screens.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedex.android.ImageBuilder

@Composable
fun PokemonCard(name: String, url: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp),
    ) {
        AsyncImage(
            model = ImageBuilder.buildPokemonImageByUrl(url),
            "Pokémon image",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Gray.copy(alpha = 0.1f))
                .padding(8.dp),
        )
        Text(
            name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray,
        )
        Spacer(modifier = Modifier.fillMaxWidth())
    }
}
