package com.senkou.practicamarvel.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.senkou.practicamarvel.PracticaMarvelApp
import com.senkou.practicamarvel.domain.character.data.CharactersRepository
import com.senkou.practicamarvel.domain.character.usecases.FavoriteToggleUseCase
import com.senkou.practicamarvel.domain.character.usecases.GetCharacterComicsUseCase
import com.senkou.practicamarvel.domain.character.usecases.GetCharacterDetailsUseCase
import com.senkou.practicamarvel.domain.character.usecases.GetCharacterListUseCase
import com.senkou.practicamarvel.framework.core.CharactersClient
import com.senkou.practicamarvel.framework.database.CharactersRoomDataSource
import com.senkou.practicamarvel.framework.network.CharacterServerDataSource
import com.senkou.practicamarvel.ui.detail.DetailScreen
import com.senkou.practicamarvel.ui.detail.DetailViewmodel
import com.senkou.practicamarvel.ui.home.HomeScreen
import com.senkou.practicamarvel.ui.home.HomeScreenViewmodel
import com.senkou.practicamarvel.ui.splash.SplashScreen

@Composable
fun Navigation() {

  val marvelDB = (LocalContext.current.applicationContext as PracticaMarvelApp).marvelDB

  val charactersRepository = CharactersRepository(
    charactersLocalDatasource = CharactersRoomDataSource(
      marvelDB.characterDao(),
      marvelDB.comicsDao()
    ),
    charactersRemoteDatasource = CharacterServerDataSource(CharactersClient.instance)
  )

  val navController = rememberNavController()
  val homeScreenViewmodel = viewModel {
    HomeScreenViewmodel(GetCharacterListUseCase(charactersRepository))
  }

  NavHost(navController = navController, startDestination = Splash) {
    composable<Splash> {
      SplashScreen(homeScreenViewmodel) {
        navController.popBackStack()
        navController.navigate(Home)
      }
    }

    composable<Home> {
      HomeScreen(homeScreenViewmodel) { character ->
        navController.navigate(Detail(character.id))
      }
    }

    composable<Detail> { backstackEntry ->
      val character = backstackEntry.toRoute<Detail>()
      DetailScreen(
        viewModel {
          DetailViewmodel(
            character.characterId,
            GetCharacterDetailsUseCase(charactersRepository),
            GetCharacterComicsUseCase(charactersRepository),
            FavoriteToggleUseCase(charactersRepository)
          )
        },
        onBack = { navController.popBackStack() }
      )
    }
  }
}