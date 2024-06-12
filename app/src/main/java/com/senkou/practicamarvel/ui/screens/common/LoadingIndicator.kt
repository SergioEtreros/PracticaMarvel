package com.senkou.practicamarvel.ui.screens.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoadingIndicator(paddingValues: PaddingValues) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(paddingValues),
    contentAlignment = Alignment.Center
  ) {
    CircularProgressIndicator()
  }
}