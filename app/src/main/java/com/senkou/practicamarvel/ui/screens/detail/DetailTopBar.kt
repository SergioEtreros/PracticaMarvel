package com.senkou.practicamarvel.ui.screens.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.senkou.practicamarvel.R
import com.senkou.practicamarvel.ui.theme.rojoMarvel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DetailTopBar(
  charName: String,
  favorite: Boolean,
  scrollBehavior: TopAppBarScrollBehavior,
  onBack: () -> Unit,
  onFavorite: () -> Unit
) {
  MediumTopAppBar(
    title = {
      Text(
        text = charName,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
      )
    },
    navigationIcon = {
      IconButton(onClick = onBack) {
        Icon(
          imageVector = Icons.AutoMirrored.Default.ArrowBack,
          contentDescription = stringResource(id = R.string.back)
        )
      }
    },
    actions = {
      IconButton(onClick = onFavorite) {
        Icon(
          tint = if (favorite) rojoMarvel else LocalContentColor.current,
          imageVector = if (favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
          contentDescription = "Favorite"
        )
      }
    },
    scrollBehavior = scrollBehavior
  )
}