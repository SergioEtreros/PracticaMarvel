package com.senkou.practicamarvel.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.senkou.practicamarvel.ui.theme.AsymetricSmall
import com.senkou.practicamarvel.ui.theme.blancoMarvel
import com.senkou.practicamarvel.ui.theme.rojoMarvel

@Composable
fun ComicItem(comic: String) {

  Box(
    modifier = Modifier.fillMaxWidth()
      .clip(AsymetricSmall)
      .background(rojoMarvel)
      .padding(8.dp)
  ) {
    Text(
      text = comic,
      color = blancoMarvel,
      style = MaterialTheme.typography.bodyLarge,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis
    )
  }
}

@Preview(showBackground = true)
@Composable
fun ComicItemPreview() {
  ComicItem(comic = "comic")
}