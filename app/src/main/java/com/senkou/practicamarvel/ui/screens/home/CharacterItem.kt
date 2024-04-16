package com.senkou.practicamarvel.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.senkou.practicamarvel.domain.model.Character
import com.senkou.practicamarvel.ui.theme.AsymetricLarge
import com.senkou.practicamarvel.ui.theme.Steel

@Composable
fun CharacterItem(
  character: Character,
  onClick: () -> Unit
) {

  Card(
    shape = AsymetricLarge,
    elevation = CardDefaults.cardElevation(
      defaultElevation = 16.dp,
    ),
    colors = CardDefaults.cardColors(containerColor = Steel),
    onClick = onClick
  ) {

    Column(
      horizontalAlignment = Alignment.CenterHorizontally
    ) {

      AsyncImage(
        model = character.imageUrl,
        contentDescription = character.name,
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .fillMaxWidth()
          .aspectRatio(2 / 3F)
//          .clip()
      )

      Text(
        text = character.name,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        modifier = Modifier.padding(8.dp)
      )
    }
  }
}