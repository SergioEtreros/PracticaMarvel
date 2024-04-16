package com.senkou.practicamarvel.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.senkou.practicamarvel.R
import com.senkou.practicamarvel.domain.model.Character
import com.senkou.practicamarvel.ui.screens.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  onItemClick: (Character) -> Unit,
  model: HomeScreenViewmodel = viewModel(),
) {
  Screen {

    val state = model.state

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
      topBar = {
        TopAppBar(
          title = {
            Text(text = stringResource(id = R.string.title))
          },
          scrollBehavior = scrollBehavior
        )
      },
      modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
      contentWindowInsets = WindowInsets.safeDrawing
    ) { paddingValues ->
      if (state.isLoading) {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
          contentAlignment = Alignment.Center
        ) {
          CircularProgressIndicator()
        }
      }

      LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(horizontal = 12.dp),
        contentPadding = paddingValues
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
}