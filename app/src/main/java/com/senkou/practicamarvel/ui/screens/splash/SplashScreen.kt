package com.senkou.practicamarvel.ui.screens.splash

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.senkou.practicamarvel.PracticaMarvelApp
import com.senkou.practicamarvel.R
import com.senkou.practicamarvel.Result
import com.senkou.practicamarvel.data.CharactersRepository
import com.senkou.practicamarvel.data.local.room.CharactersLocalDataSource
import com.senkou.practicamarvel.data.network.marvel.CharactersRemoteDataSource
import com.senkou.practicamarvel.ui.screens.Screen
import com.senkou.practicamarvel.ui.screens.home.HomeScreenViewmodel
import com.senkou.practicamarvel.ui.theme.rojoMarvel
import com.senkou.practicamarvel.usecase.GetCharacterListUseCase
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
  model: HomeScreenViewmodel,
  navigateMainScreen: () -> Unit
) {

  val state by model.state.collectAsState()

  LaunchedEffect(state is Result.Success) {
    delay(250)
    navigateMainScreen()
  }

  Screen {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(rojoMarvel),
      contentAlignment = Alignment.Center
    ) {
      Image(
        painter = painterResource(id = R.drawable.marvle_m),
        contentDescription = null,
        modifier = Modifier.size(200.dp)
      )
    }
  }
}

@Preview(
  showBackground = true,
  showSystemUi = true,
  uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SplashScreenPreview() {

  val marvelDB = (LocalContext.current.applicationContext as PracticaMarvelApp).marvelDB
  val charactersRepository = CharactersRepository(
    charactersLocalDatasource = CharactersLocalDataSource(
      marvelDB.characterDao(),
      marvelDB.comicsDao()
    ),
    charactersRemoteDatasource = CharactersRemoteDataSource
  )

  SplashScreen(
    viewModel {
      HomeScreenViewmodel(GetCharacterListUseCase(charactersRepository))
    }
  ) {}
}