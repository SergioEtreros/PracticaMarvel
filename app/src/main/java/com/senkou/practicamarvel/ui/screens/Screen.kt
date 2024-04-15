package com.senkou.practicamarvel.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.senkou.practicamarvel.ui.theme.PracticaMarvelTheme

@Composable
fun Screen(content: @Composable () -> Unit) {
  PracticaMarvelTheme {
    // A surface container using the 'background' color from the theme
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colorScheme.background,
      content = content
    )
  }
}
