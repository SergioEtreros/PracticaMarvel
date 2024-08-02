package com.senkou.practicamarvel.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

@Composable
fun CharacterItem(
  character: com.senkou.practicamarvel.domain.character.entities.Character,
  onClick: () -> Unit
) {

  Card(
    shape = com.senkou.practicamarvel.ui.common.theme.AsymetricLarge,
    elevation = CardDefaults.cardElevation(
      defaultElevation = 16.dp,
    ),
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
      )

      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp)
      ) {

        Text(
          text = character.name,
          style = MaterialTheme.typography.bodyLarge,
          fontWeight = FontWeight.Bold,
          maxLines = 1,
          modifier = Modifier.weight(1f)
        )

        com.senkou.practicamarvel.ui.common.FavoriteIcon(
          favorite = character.favorite
        )
      }
    }
  }
}