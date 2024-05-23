package com.senkou.practicamarvel.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.senkou.practicamarvel.data.network.marvel.CharactersRepository
import com.senkou.practicamarvel.ui.screens.detail.DetailScreen
import com.senkou.practicamarvel.ui.screens.detail.DetailViewmodel
import com.senkou.practicamarvel.ui.screens.home.HomeScreen
import com.senkou.practicamarvel.ui.screens.home.HomeScreenViewmodel
import com.senkou.practicamarvel.ui.screens.splash.SplashScreen
import com.senkou.practicamarvel.usecase.GetCharacterDetailsUseCase
import com.senkou.practicamarvel.usecase.GetCharacterListUseCase

@Composable
fun Navigation() {

  val navController = rememberNavController()
  val homeScreenViewmodel = viewModel {
    HomeScreenViewmodel(GetCharacterListUseCase(CharactersRepository()))
  }

  NavHost(navController = navController, startDestination = Splash) {
    composable<Splash> {
      SplashScreen(homeScreenViewmodel){
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
            GetCharacterDetailsUseCase(CharactersRepository())
          )
        },
        onBack = { navController.popBackStack() }
      )
    }
  }
}