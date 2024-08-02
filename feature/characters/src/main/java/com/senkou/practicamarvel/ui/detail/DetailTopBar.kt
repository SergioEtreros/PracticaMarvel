package com.senkou.practicamarvel.ui.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.senkou.practicamarvel.ui.characters.R
import com.senkou.practicamarvel.ui.common.FavoriteIcon

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
        FavoriteIcon(favorite)
      }
    },
    scrollBehavior = scrollBehavior
  )
}

