package com.senkou.practicamarvel.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.senkou.practicamarvel.ui.screens.Screen

@Composable
fun DetailScreen(vm: DetailViewmodel, onBack: () -> Unit) {

  val state = vm.state

  Screen {

    state.character?.let { character ->

      Column(modifier = Modifier.fillMaxSize()) {

        AsyncImage(
          model = character.imageUrl,
          contentDescription = character.name,
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
        )

        Text(
          text = character.name,
          modifier = Modifier.padding(16.dp),
          style = MaterialTheme.typography.headlineMedium
        )
      }
    }
  }
}