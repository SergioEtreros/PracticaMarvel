package com.senkou.practicamarvel.ui.screens.splash

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.senkou.practicamarvel.R
import com.senkou.practicamarvel.data.network.marvel.CharactersRepository
import com.senkou.practicamarvel.ui.screens.Home
import com.senkou.practicamarvel.ui.screens.Screen
import com.senkou.practicamarvel.ui.screens.home.HomeScreenViewmodel
import com.senkou.practicamarvel.ui.theme.rojoMarvel
import com.senkou.practicamarvel.usecase.GetCharacterListUseCase

@Composable
fun SplashScreen(
  model: HomeScreenViewmodel,
  navigateMainScreen: () -> Unit
) {

  LaunchedEffect(true) {
    model.loadList{
      navigateMainScreen()
    }
  }

  Screen {

    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(rojoMarvel),
      contentAlignment = Alignment.Center
    ) {
      Image(painter = painterResource(id = R.drawable.marvle_m), contentDescription = null, modifier = Modifier.size(200.dp))

//      Text(
//        text = stringResource(R.string.app_name),
//        style = MaterialTheme.typography.headlineLarge,
//        color = MaterialTheme.colorScheme.onSurface
//      )
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
  val navController = rememberNavController()
  SplashScreen(
    viewModel {
      HomeScreenViewmodel(GetCharacterListUseCase(CharactersRepository()))}
  ){
    navController.popBackStack()
    navController.navigate(Home)
  }
}