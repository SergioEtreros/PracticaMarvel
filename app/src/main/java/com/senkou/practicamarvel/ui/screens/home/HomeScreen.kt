package com.senkou.practicamarvel.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.senkou.practicamarvel.domain.model.Character
import com.senkou.practicamarvel.ui.screens.Screen

@Composable
fun HomeScreen(
  onItemClick: (Character) -> Unit,
  model: HomeScreenViewmodel = viewModel(),
) {
  Screen {

    val state = model.state

    if (state.isLoading) {
      Box(
        modifier = Modifier
          .fillMaxSize(),
//          .padding(paddingValues),
        contentAlignment = Alignment.Center
      ) {
        CircularProgressIndicator()
      }
    }


    LazyVerticalGrid(
      columns = GridCells.Adaptive(180.dp),
      horizontalArrangement = Arrangement.spacedBy(4.dp),
      verticalArrangement = Arrangement.spacedBy(4.dp),
      modifier = Modifier.padding(horizontal = 4.dp),
    ) {

      items(state.characters) { character ->
        CharacterItem(
          character = character,
          onClick = { onItemClick(character) }
        )


      }
    }
  }
}