package com.senkou.practicamarvel.ui.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.senkou.practicamarvel.PracticaMarvelApp
import com.senkou.practicamarvel.R
import com.senkou.practicamarvel.data.CharactersRepository
import com.senkou.practicamarvel.data.local.room.CharactersLocalDataSource
import com.senkou.practicamarvel.data.network.marvel.CharactersRemoteDataSource
import com.senkou.practicamarvel.domain.model.Character
import com.senkou.practicamarvel.ui.screens.Screen
import com.senkou.practicamarvel.ui.screens.common.MarvelScaffold
import com.senkou.practicamarvel.usecase.GetCharacterListUseCase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  model: HomeScreenViewmodel,
  onItemClick: (Character) -> Unit,
) {
  Screen {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val state by model.state.collectAsState()

    MarvelScaffold(
      state = state,
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
    ) { paddingValues, characters ->

      LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(horizontal = 12.dp),
        contentPadding = paddingValues
      ) {

        items(characters) { character ->
          CharacterItem(
            character = character,
            onClick = { onItemClick(character) }
          )
        }
      }
    }
  }
}

@Preview(
  showBackground = true,
  showSystemUi = true,
  uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun HomeScreenPreview() {

  val marvelDB = (LocalContext.current.applicationContext as PracticaMarvelApp).marvelDB
  val charactersRepository = CharactersRepository(
    charactersLocalDatasource = CharactersLocalDataSource(
      marvelDB.characterDao(),
      marvelDB.comicsDao()
    ),
    charactersRemoteDatasource = CharactersRemoteDataSource
  )

  Screen {
    HomeScreen(
      model = HomeScreenViewmodel(GetCharacterListUseCase(charactersRepository)),
      onItemClick = {}
    )
  }
}