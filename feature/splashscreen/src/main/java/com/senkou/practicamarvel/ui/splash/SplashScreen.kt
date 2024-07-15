package com.senkou.practicamarvel.ui.splash

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.senkou.practicamarvel.ui.common.Result
import com.senkou.practicamarvel.ui.common.Screen
import com.senkou.practicamarvel.ui.common.theme.rojoMarvel
import com.senkou.practicamarvel.ui.home.HomeScreenViewmodel
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

//@Preview(
//  showBackground = true,
//  showSystemUi = true,
//  uiMode = Configuration.UI_MODE_NIGHT_YES
//)
//@Composable
//fun SplashScreenPreview() {
//
//  val marvelDB = (LocalContext.current.applicationContext as PracticaMarvelApp).marvelDB
//  val charactersRepository = CharactersRepository(
//    charactersLocalDatasource = CharactersRoomDataSource(
//      marvelDB.characterDao(),
//      marvelDB.comicsDao()
//    ),
//    charactersRemoteDatasource = CharacterServerDataSource(CharactersClient.instance)
//  )
//
//  SplashScreen(
//    viewModel {
//      com.senkou.practicamarvel.ui.home.HomeScreenViewmodel(
//        com.senkou.practicamarvel.domain.character.usecases.GetCharacterListUseCase(
//          charactersRepository
//        )
//      )
//    }
//  ) {}
//}