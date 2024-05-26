package com.senkou.practicamarvel.ui.screens.detail

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ComicItem(image: String) {

  Card(
    shape = RoundedCornerShape(6.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
  ) {
    AsyncImage(
      model = image,
      contentDescription = image,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(6.dp))
        .aspectRatio(2 / 3F)
    )
  }
}

@Preview(showBackground = true)
@Composable
fun ComicItemPreview() {
  ComicItem(image = "comic")
}