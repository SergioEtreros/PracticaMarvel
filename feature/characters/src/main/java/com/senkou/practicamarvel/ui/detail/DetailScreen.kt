package com.senkou.practicamarvel.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.senkou.practicamarvel.ui.common.ifSuccess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(vm: DetailViewmodel, onBack: () -> Unit) {

  com.senkou.practicamarvel.ui.common.Screen {

    val state by vm.state.collectAsState()
    val detailScreenState = rememberDetailScreenState()

    com.senkou.practicamarvel.ui.common.MarvelScaffold(
      state = state,
      topBar = {
        state.ifSuccess {
          DetailTopBar(
            charName = it.character?.name.orEmpty(),
            favorite = it.character?.favorite ?: false,
            scrollBehavior = detailScreenState.scrollBehavior,
            onBack = onBack,
            onFavorite = { vm.onFavoriteClick() }
          )
        }
      },
      snackbarHost = { SnackbarHost(hostState = detailScreenState.snackbarHostState) },
      modifier = Modifier.nestedScroll(detailScreenState.scrollBehavior.nestedScrollConnection),
      contentWindowInsets = WindowInsets.safeDrawing
    ) { paddingValues, stateSucces ->

      val character = stateSucces.character

      detailScreenState.ShowMessageEffect(message = stateSucces.message) {
        vm.onMessageShown()
      }

      character?.let {
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .padding(horizontal = 16.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Spacer(modifier = Modifier.height(16.dp))

          Card(
            modifier = Modifier.fillMaxWidth(),
            shape = com.senkou.practicamarvel.ui.common.theme.AsymetricLarge,
            elevation = CardDefaults.cardElevation(
              defaultElevation = 8.dp,
            )
          ) {

            AsyncImage(
              model = character.imageUrl,
              contentDescription = character.name,
              contentScale = ContentScale.Crop,
              modifier = Modifier
                .aspectRatio(16f / 9f)
            )
          }

          Spacer(modifier = Modifier.height(24.dp))

          if (character.description.isNotEmpty()) {
            Text(
              text = character.description,
              style = MaterialTheme.typography.bodyLarge,
              textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(24.dp))
          }

          ComicGrid(stateSucces.comics)
        }
      }
    }
  }
}







