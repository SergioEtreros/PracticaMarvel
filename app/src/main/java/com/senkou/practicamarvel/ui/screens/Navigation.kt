package com.senkou.practicamarvel.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.senkou.practicamarvel.PracticaMarvelApp
import com.senkou.practicamarvel.data.CharactersRepository
import com.senkou.practicamarvel.data.local.room.CharactersLocalDataSource
import com.senkou.practicamarvel.data.network.marvel.CharactersRemoteDataSource
import com.senkou.practicamarvel.ui.screens.detail.DetailScreen
import com.senkou.practicamarvel.ui.screens.detail.DetailViewmodel
import com.senkou.practicamarvel.ui.screens.home.HomeScreen
import com.senkou.practicamarvel.ui.screens.home.HomeScreenViewmodel
import com.senkou.practicamarvel.ui.screens.splash.SplashScreen
import com.senkou.practicamarvel.usecase.FavoriteToggleUseCase
import com.senkou.practicamarvel.usecase.GetCharacterComicsUseCase
import com.senkou.practicamarvel.usecase.GetCharacterDetailsUseCase
import com.senkou.practicamarvel.usecase.GetCharacterListUseCase

@Composable
fun Navigation() {

  val marvelDB = (LocalContext.current.applicationContext as PracticaMarvelApp).marvelDB

  val charactersRepository = CharactersRepository(
    charactersLocalDatasource = CharactersLocalDataSource(
      marvelDB.characterDao(),
      marvelDB.comicsDao()
    ),
    charactersRemoteDatasource = CharactersRemoteDataSource
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