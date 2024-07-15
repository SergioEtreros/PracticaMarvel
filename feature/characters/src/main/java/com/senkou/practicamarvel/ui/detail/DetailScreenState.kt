package com.senkou.practicamarvel.ui.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

class DetailScreenState @OptIn(ExperimentalMaterial3Api::class) constructor(
  val snackbarHostState: SnackbarHostState,
  val scrollBehavior: TopAppBarScrollBehavior
) {

  @Composable
  fun ShowMessageEffect(message: String?, onMessageShown: () -> Unit) {
    LaunchedEffect(message) {
      message?.let {
        snackbarHostState.currentSnackbarData?.dismiss()
        snackbarHostState.showSnackbar(it)
        onMessageShown()
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberDetailScreenState(
  snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
  scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
) = remember(snackbarHostState, scrollBehavior) {
  DetailScreenState(snackbarHostState, scrollBehavior)
}

