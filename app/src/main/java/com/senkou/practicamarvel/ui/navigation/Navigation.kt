package com.senkou.practicamarvel.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.senkou.practicamarvel.ui.common.Detail
import com.senkou.practicamarvel.ui.common.Home
import com.senkou.practicamarvel.ui.common.Splash
import com.senkou.practicamarvel.ui.detail.DetailScreen
import com.senkou.practicamarvel.ui.home.HomeScreen
import com.senkou.practicamarvel.ui.splash.SplashScreen

@Composable
fun Navigation() {

   val navController = rememberNavController()

   NavHost(navController = navController, startDestination = Splash) {
      composable<Splash> {
         SplashScreen {
            navController.popBackStack()
            navController.navigate(Home)
         }
      }

      composable<Home> {
         HomeScreen { character ->
            navController.navigate(Detail(character.id))
         }
      }

      composable<Detail> {
         DetailScreen(
            onBack = { navController.popBackStack() }
         )
      }
   }
}