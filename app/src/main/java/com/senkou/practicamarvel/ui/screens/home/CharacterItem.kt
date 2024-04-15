package com.senkou.practicamarvel.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.senkou.practicamarvel.domain.data.Character

@Composable
fun CharacterItem(
  character: Character,
  onClick: () -> Unit
) {

  Card(
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
    onClick = onClick
  ) {

    Column(
      horizontalAlignment = Alignment.CenterHorizontally
    ) {

      AsyncImage(
        model = character.imageUrl,
        contentDescription = character.name,
        modifier = Modifier
          .fillMaxWidth()
          .aspectRatio(2 / 3F)
          .clip(MaterialTheme.shapes.small)
      )

      Text(
        text = character.name,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 1,
        modifier = Modifier.padding(8.dp)
      )
    }
  }
}