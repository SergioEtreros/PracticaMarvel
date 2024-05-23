package com.senkou.practicamarvel.ui.screens.splash

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.senkou.practicamarvel.R
import com.senkou.practicamarvel.ui.screens.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateMainScreen: () -> Unit) {

  LaunchedEffect(true) {
    delay(1500)
    navigateMainScreen()
  }

  Screen {

    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = stringResource(R.string.app_name),
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onSurface
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
  SplashScreen(navigateMainScreen = { })
}