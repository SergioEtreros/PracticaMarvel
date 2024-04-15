package com.senkou.practicamarvel.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.senkou.practicamarvel.ui.screens.detail.DetailScreen
import com.senkou.practicamarvel.ui.screens.detail.DetailViewmodel
import com.senkou.practicamarvel.ui.screens.home.HomeScreen

const val PARAMETER_CHARACTER_ID = "characterId"

@Composable
fun Navigation() {

  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = "home") {
    composable(route = "home") {
      HomeScreen(onItemClick = { character ->
        navController.navigate("detail/${character.id}")
      })
    }

    composable(
      route = "detail/{$PARAMETER_CHARACTER_ID}",
      arguments = listOf(navArgument(PARAMETER_CHARACTER_ID) { type = NavType.IntType })
    ) {backstackEntry ->
      val characterId = requireNotNull(backstackEntry.arguments?.getInt(PARAMETER_CHARACTER_ID))
      DetailScreen(
        viewModel {DetailViewmodel(characterId)},
        onBack = { navController.popBackStack() }
      )
    }
  }
}