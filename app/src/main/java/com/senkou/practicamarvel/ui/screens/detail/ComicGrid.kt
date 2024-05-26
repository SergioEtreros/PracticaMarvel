package com.senkou.practicamarvel.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.senkou.practicamarvel.R

@Composable
fun ComicGrid(comics: List<String>) {

  Text(
    text = stringResource(R.string.comics),
    style = MaterialTheme.typography.headlineMedium
  )

  Spacer(modifier = Modifier.height(16.dp))

  LazyVerticalGrid(
    columns = GridCells.Adaptive(120.dp),
    horizontalArrangement = Arrangement.spacedBy(12.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    contentPadding = PaddingValues(bottom = 24.dp)
  ) {

    items(comics) { image ->
      ComicItem(image = image)
    }
  }
}